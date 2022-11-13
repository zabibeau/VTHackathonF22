from sklearn.feature_extraction.text import CountVectorizer
from sklearn.naive_bayes import MultinomialNB
import pandas as pd
import numpy


#This program creates a ML model that will determine which sports team someone is a fan of based off of their
#tweets

#This function creates a DataFrame from the txt files
def getDataFrameFromFiles(fileName1, fileName2):
    file1 = open(fileName1, 'r')
    file2 = open(fileName2, 'r')
    index1 = fileName1.find("Training")
    slash1 = fileName1.rfind("/")
    index2 = fileName2.find("Training")
    slash2 = fileName2.rfind("/")
    if (index1 != -1 and index2 != -1):
        teams = []
        tweets = []
        for tweet in file1.readlines():
            teams.append(fileName1[slash1 + 1:index1])
            tweets.append(tweet)
        for tweet in file2.readlines():
            teams.append(fileName2[slash2 + 1:index2])
            tweets.append(tweet)
        d = {"Team":teams, "Tweet":tweets}
        data = pd.DataFrame(data=d)
        return data



#Here we use a CountVectorizer and MultinomialNB objects to do most of the heavy lifting
team1 = "Rivalries/Ohio StateMichigan/MichiganTrainingTweets.txt"
team2 = "Rivalries/Ohio StateMichigan/Ohio StateTrainingTweets.txt"
sample = ["i will be camping at louisiana tech this friday ready to compete & get some great work! @scumbie_latech"]
vectorizer = CountVectorizer()
data = getDataFrameFromFiles(team1, team2)
counts = vectorizer.fit_transform(data["Tweet"].values)
targets = data["Team"].values
classifier = MultinomialNB()
classifier.fit(counts, targets)
tweetCounts = vectorizer.transform(sample)
prediction = classifier.predict(tweetCounts)
predictionChance = classifier.predict_proba(tweetCounts)
print(sample)
print(prediction)
print(predictionChance)





