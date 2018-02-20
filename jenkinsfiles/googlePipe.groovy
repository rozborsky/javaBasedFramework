echo "hey!"

stage 'Build'

node {
    publishHTML(
            [allowMissing: false,
             alwaysLinkToLastBuild: false,
             keepAll: true,
             reportDir: '',
             reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: ''])
}