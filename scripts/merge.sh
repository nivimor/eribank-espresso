echo ${TRAVIS_BRANCH}
echo "switching to master"
git checkout -b master
echo "pulling master"
git pull origin master
echo "merging ${TRAVIS_BRANCH} to master"
git merge ${BRANCH}
git commit -am "${TRAVIS_COMMIT_MESSAGE} and merged to master"
echo "pushing to master"
git push https://${GIT_AUTH}@github.com/nivimor/eribank-espresso.git master --force