echo "hey!"

publishHTML(
        [allowMissing: false,
         alwaysLinkToLastBuild: false,
         keepAll: false,
         reportDir: 'target',
         reportFiles: '*.html',
         reportName: 'HTML Report',
         reportTitles: ''])