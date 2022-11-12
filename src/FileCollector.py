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
def getTrainingTweets(handle1, team1, handle2, team2):
    
    #Creates a directory for the tweets and handles
    directoryName = team1 + team2
    print(os.getcwd())
    os.chdir("Rivalries")
    os.mkdir(directoryName)
    os.chdir(directoryName)

    #Finds handles and tweets for the first team of the rivalry
    handleFile = open(team1 + "TrainingHandles.txt", 'w')
    tweetFile = open(team1 + "TrainingTweets.txt", 'w')
    totalTweets = 0
    count = 0
    pages = tweepy.Cursor(api.get_followers, screen_name=handle1, count=200).pages(10000)
    for page in pages:
        for follower in page:
            if follower.statuses_count > 0: 

                try:
                    tweets = api.user_timeline(screen_name=follower.screen_name, exclude_replies=False, include_rts=True, tweet_mode="extended")
                except tweepy.errors.Unauthorized:
                    print("An error happened :(")
                else:
                    handleFile.write(follower.screen_name + "\n")

                    for tweet in tweets:
                        tweetFile.write(str(clean(tweet.full_text, no_emoji=True, no_line_breaks=True, normalize_whitespace=True)) + "\n")
                        count += 1
            if(count > 5000):
                handleFile.close()
                tweetFile.close()
                return
        time.sleep(10)


    #Finds handles and tweets for the second team in the rivalry
    handleFile = open(team2 + "TrainingHandles.txt", 'w')
    tweetFile = open(team2 + "TrainingTweets.txt", 'w')
    count = 0
    pages = tweepy.Cursor(api.get_followers, screen_name=handle2, count=200).pages(10000)
    for page in pages:
        for follower in page:
            if follower.statuses_count > 0:
                try:
                    tweets = api.user_timeline(screen_name=follower.screen_name, exclude_replies=False, include_rts=True, tweet_mode="extended")
                except tweepy.errors.Unauthorized:
                    print("An error happened :(")
                else:
                    handleFile.write(follower.screen_name + "\n")

                    for tweet in tweets:
                        tweetFile.write(str(clean(tweet.full_text, no_emoji=True, no_line_breaks=True, normalize_whitespace=True)) + "\n")
                        count += 1
            if(count > 5000):
                handleFile.close()
                tweetFile.close()
                return
        time.sleep(10)

getTrainingTweets("@HokiesFB", "VirginiaTech", "@UVAFootball", "Virginia")

