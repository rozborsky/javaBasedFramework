echo "hey!"

stage 'Build'

node {

    sh 'bundle install'

    // build and run tests with coverage
    sh 'bundle exec rake build spec'

    // Archive the built artifacts
    archive (includes: 'pkg/*.gem')
    
    publishHTML([
            allowMissing: false,
            alwaysLinkToLastBuild: false,
            keepAll: false,
            reportDir: 'target', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: ''])
}