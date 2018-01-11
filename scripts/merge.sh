echo ${TRAVIS_BRANCH}
git stash
echo "switching to master"
git checkout -b master
echo "merging ${TRAVIS_BRANCH} to master"
git merge ${BRANCH}
echo "pulling master"
git pull origin master
echo "pushing to master"
git push https://${GIT_AUTH}@github.com/nivimor/eribank-espresso.git master --force