echo "hey!"

stage 'Build'

node {
    publishHTML([
            allowMissing: false,
            alwaysLinkToLastBuild: false,
            keepAll: false,
            reportDir: 'target', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: ''])
}