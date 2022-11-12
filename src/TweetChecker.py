import sklearn.feature_extraction as skf
import sklearn.naive_bayes as skn
import pandas as pd

def getDataFrameFromFiles(fileName1, fileName2):
    file1 = open(fileName1, 'r')
    file2 = open(fileName2, 'r')
    index1 = fileName1.find("Training")
    index2 = fileName2.find("Training")
    if (index1 != -1 and index2 != -1):
        teams = []
        tweets = []
        for tweet in file1.readlines():
            teams.append(fileName1[0:index1])
            tweets.append(tweet)
        for tweet in file2.readlines():
            teams.append(fileName2[0:index2])
            tweets.append(tweet)
        d = {teams, tweets}
        data = pd.DataFrame(data=d, columns=["Team", "Tweet"])
        return data

def createModel(teamFile1, teamFile2):
    vectorizer = skf.feature_extraction.CountVectorizer()
    data = getDataFrameFromFiles(teamFile1, teamFile2)
    counts = vectorizer.fit_transform(data["Tweet"].values)
    targets = data["Team"].values
    classifier = skn.naive_bayes.MultinomialNB()