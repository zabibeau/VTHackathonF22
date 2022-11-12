import tweepy
from cleantext import clean
import os
import time

API_KEY = "rMOagNUNK85bd74sYY18yM2nJ"
API_SECRET = "Psipo3rdVRhVmnLj2DlvFwIyw2EEAtLr1ovk80C57fUeEsT5EX"
BEARER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAEPbjAEAAAAAi0mn%2Bwou6q2IXXDx2hAT%2BxxkIt4%3D0OyZuxOKjezAbPWhV01yq9cGqMRq8e0t7z33AcLbBLDuOhR0mZ"
ACCESS_TOKEN = "1574567251994353665-EVXcZI8rgerCDBZAUucRQbE0qQxmTE"
ACCESS_TOKEN_SECRET = "oLZsrYvbFaxpW9JbP4e31B9LmAHO5zVLWlx39NilcWKZj"

auth = tweepy.OAuthHandler(API_KEY, API_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET)
api = tweepy.API(auth, retry_count=10, wait_on_rate_limit=True, )

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
    getTrainingTweets(team1, team2, handle1, gameFile)
    getTrainingTweets(team2, team1, handle2, gameFile)
    getGameTweets(team1, team2, handle1, gameFile)
    getGameTweets(team2, team1, handle2, gameFile)

    os.chdir("../..")
    print(os.getcwd())

def getGameTweets(team1, team2, handle, file):
    pages = tweepy.Cursor(api.get_followers, screen_name=handle, count=200).pages(10000)
    i = 0
    numTweets = 0
    for page in pages:
        i += 1
        if (i > 100):
            for follower in page:
                try:
                    tweets = api.user_timeline(screen_name=follower.screen_name, exclude_replies=False, include_rts=False, tweet_mode="extended")
                except tweepy.errors.Unauthorized:
                    print("An error occurred")
                else:
                    for tweet in tweets:
                            file.write(str(clean(team1 + " " + team2 + " " + tweet.full_text, no_emoji=True, no_line_breaks=True, normalize_whitespace=True)) + "\n")
                            numTweets += 1
                            if numTweets > 50:
                                return numTweets



def getTrainingTweets(team1, team2, handle, gameFile):
    handleFile = open(team1 + "TrainingHandles.txt", 'w')
    tweetFile = open(team1 + "TrainingTweets.txt", 'w')
    count1 = 0
    pages = tweepy.Cursor(api.get_followers, screen_name=handle, count=200).pages(10000)
    for page in pages:
        for follower in page:
            if(count1 < 100):
                if follower.statuses_count > 0: 

                    try:
                        tweets = api.user_timeline(screen_name=follower.screen_name, exclude_replies=False, include_rts=True, tweet_mode="extended", count=100)
                    except tweepy.errors.Unauthorized:
                        print("An error happened :(")
                    else:
                        handleFile.write(follower.screen_name + "\n")

                        for tweet in tweets:
                            tweetFile.write(str(clean(tweet.full_text, no_emoji=True, no_line_breaks=True, normalize_whitespace=True)) + "\n")
                            count1 += 1
            else:
                handleFile.close()
                tweetFile.close()
                print(count1)
                return   
                
        time.sleep(10)


def getData():
    rivalNameFile = open("Planning/Rivalries.txt", 'r')
    rivalHandleFile = open("Planning/RivalryHandles.txt", 'r')
    count = 0
    names = rivalNameFile.readlines()
    handles = rivalHandleFile.readlines()
    for i in range(0, 25):
        names[i] = names[i].replace(" ", "")
        names[i] = names[i].replace("\n", "")
        names[i] = names[i].split("vs.")
        handles[i] = handles[i].split(" ")
        getTweets(handles[i][0], names[i][0], handles[i][1], names[i][1], count)

    rivalNameFile.close()
    rivalHandleFile.close()


getData()

