import tweepy
from cleantext import clean
import os
import time

#These keys and tokens are used to authenticate requests to Twitter's server.
API_KEY = "Key"
API_SECRET = "Secret"
BEARER_TOKEN = "Token"
ACCESS_TOKEN = "Token"
ACCESS_TOKEN_SECRET = "Secret"

auth = tweepy.OAuthHandler(API_KEY, API_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET)
api = tweepy.API(auth, retry_count=10, wait_on_rate_limit=True)

#This function will gather 5000 tweets from a specific team to be used to train the ML algorithm.
def getTrainingTweets(team, page, numTweets):
    handleFile = open(team + "TrainingHandles.txt", 'w')
    tweetFile = open(team + "TrainingTweets.txt", 'w')
    for follower in page:
        if follower.statuses_count > 0:
            try:
                tweets = api.user_timeline(screen_name=follower.screen_name, exclude_replies=False, include_rts=True, count=200)
            except tweepy.errors.Unauthorized:
                print(":(")
            else:
                handleFile.write(follower.screen_name + "\n")
                for tweet in tweets:
                    tweetFile.write(str(clean(tweet.text, normalize_whitespace=True, no_emoji=True, no_line_breaks=True)) + "\n")
                    numTweets += 1
                    if (numTweets > 5000):
                        return numTweets
    return numTweets

#This function will get 200 tweets to be used as testing data.
def getGameTweets(rightTeam, wrongTeam, outputFile, page, numTweets):
    for follower in page:
        try:
            tweets = api.user_timeline(screen_name=follower.screen_name, exclude_replies=False, include_rts=True, count=100)
        except tweepy.errors.Unauthorized:
            print(":(")
        else:
            for tweet in tweets:
                outputFile.write(str(clean(rightTeam + " " + wrongTeam + " " + tweet.text, normalize_whitespace=True, no_emoji=True, no_line_breaks=True)) + "\n")
                numTweets += 1
                if(numTweets > 5200):
                    return numTweets
    return numTweets


#This function gets the tweets used for training the ML algorithm
#The user inputs two handles and two team names and a directory will be created containing 
#handles and tweets from followers of both teams.
def getTweets(handle1, team1, handle2, team2, count):
    
    #Creates a directory for the tweets and handles
    os.chdir("GameTweets")
    os.mkdir(str(count))
    os.chdir(str(count))
    gameFile = open("gameTweets.txt", 'w')
    os.chdir("../..")
    directoryName = team1 + team2
    os.chdir("Rivalries")
    os.mkdir(directoryName)
    os.chdir(directoryName)

    #Finds handles and tweets for both teams in the rivalry
    pages = tweepy.Cursor(api.get_followers, screen_name=handle1, count=200).pages(10000)
    numTweets = 0
    for page in pages:
        if (numTweets < 5000):
            numTweets = getTrainingTweets(team1, page, numTweets)
        elif(numTweets <= 5200):
            numTweets = getGameTweets(team1, team2, gameFile, page, numTweets)
        else:
            break;

    pages2 = tweepy.Cursor(api.get_followers, screen_name=handle2, count=200).pages(10000)
    numTweets=0
    for page in pages2:
        if (numTweets < 5000):
            numTweets = getTrainingTweets(team2, page, numTweets)
        elif(numTweets <= 5200):
            numTweets = getGameTweets(team2, team1, gameFile, page, numTweets)
        else:
            break;

    #Resets the current working directory to the home directory.
    os.chdir("../..")
    print(os.getcwd())

