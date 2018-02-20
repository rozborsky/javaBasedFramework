echo "hey!"

stage 'Build'

node {
    // Checkout
    git 'https://github.com/rozborsky/javaBasedFramework.git'

    // install required bundles
    sh 'bundle install'

    // build and run tests with coverage
    sh 'bundle exec rake build spec'

    // Archive the built artifacts
    archive (includes: 'pkg/*.gem')

    publishHTML(
            [allowMissing: false,
             alwaysLinkToLastBuild: false,
             keepAll: true,
             reportDir: 'target',
             reportFiles: '*.html', reportName: 'HTML Report', reportTitles: ''])
}