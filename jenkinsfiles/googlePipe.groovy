echo "hey!"

node ('master'){
    checkout scm

    bat "mvn clean install -Dconfig=google_search -Dtests=google_search_suite.xml"

    publishHTML([
            allowMissing: false,
            alwaysLinkToLastBuild: false,
            keepAll: false,
            reportDir: 'target', reportFiles: 'Report*.html', reportName: 'HTML Report', reportTitles: ''])
}