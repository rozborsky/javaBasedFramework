echo "hey!"

stage 'Build'

node {

    git 'https://github.com/rozborsky/javaBasedFramework.git'

    sh 'bundle install'

    // build and run tests with coverage
    sh 'bundle exec rake build spec'

    // Archive the built artifacts
    archive (includes: 'pkg/*.gem')

    publishHTML([
            allowMissing: false,
            alwaysLinkToLastBuild: false,
            keepAll: false,
            reportDir: 'coverage', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: ''])
}