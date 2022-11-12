import tweepy
from cleantext import clean
import os

API_KEY = "rMOagNUNK85bd74sYY18yM2nJ"
API_SECRET = "Psipo3rdVRhVmnLj2DlvFwIyw2EEAtLr1ovk80C57fUeEsT5EX"
BEARER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAEPbjAEAAAAAi0mn%2Bwou6q2IXXDx2hAT%2BxxkIt4%3D0OyZuxOKjezAbPWhV01yq9cGqMRq8e0t7z33AcLbBLDuOhR0mZ"
ACCESS_TOKEN = "1574567251994353665-EVXcZI8rgerCDBZAUucRQbE0qQxmTE"
ACCESS_TOKEN_SECRET = "oLZsrYvbFaxpW9JbP4e31B9LmAHO5zVLWlx39NilcWKZj"

auth = tweepy.OAuthHandler(API_KEY, API_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET)
api = tweepy.API(auth)

#This function gets the tweets used for training the ML algorithm
#The user inputs two handles and two team names and a directory will be created containing 
#handles and tweets from followers of both teams.
def getTrainingTweets(handle1, team1, handle2, team2):
    
    #Creates a directory for the tweets and handles
    directoryName = team1 + team2
    path = os.path.join("../Rivalries/", directoryName)
    os.mkdir(path)
    os.chdir(path)

    #Finds handles and tweets for the first team of the rivalry
    handleFile = open(team1 + "TrainingHandles.txt", 'w')
    totalTweets = 0
    count = 0
    followers = api.get_followers(screen_name = handle1)
    while (followers[count] != None and totalTweets <= 5000):
        if (followers[count].status_count > 0):
            handleFile.write(followers[count].screen_name + "\n")
        totalTweets += followers[count].status_count
        count+= 1
    handleFile.close()
    handleFile = open(team1 + "TrainingHandles.txt", 'r')
    tweetFile = open(team1 + "TrainingTweets.txt", 'w')
    for follower in handleFile.readlines():
        tweets = api.user_timeline(follower, include_replies=True, include_rts=True, tweet_mode="extended")
        for tweet in tweets:
            tweetFile.write(str(clean(tweet.full_text, no_emoji=True, no_line_breaks=True, normalize_whitespace=True)) + "\n")
    handleFile.close()
    tweetFile.close()

    #Finds handles and tweets for the second team in the rivalry
    handleFile = open(team2 + "TrainingHandles.txt", 'w')
    totalTweets = 0
    count = 0
    followers = api.get_followers(screen_name = handle2)
    while (followers[count] != None and totalTweets <= 5000):
        if (followers[count].status_count > 0):
            handleFile.write(followers[count].screen_name + "\n")
        totalTweets += followers[count].status_count
        count+= 1
    handleFile.close()
    handleFile = open(team2 + "TrainingHandles.txt", 'r')
    tweetFile = open(team2 + "TrainingTweets.txt", 'w')
    for follower in handleFile.readlines():
        tweets = api.user_timeline(follower, include_replies=True, include_rts=True, tweet_mode="extended")
        for tweet in tweets:
            tweetFile.write(str(clean(tweet.full_text, no_emoji=True, no_line_breaks=True, normalize_whitespace=True)) + "\n")
    handleFile.close()
    tweetFile.close()

