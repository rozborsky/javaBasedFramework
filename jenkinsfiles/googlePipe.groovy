echo "hey!"

stage 'Build'

node {
    checkout scm

    bat "mvn clean install -Dconfig=google_search -Dtests=google_search.xml"
    publishHTML([
            allowMissing: false,
            alwaysLinkToLastBuild: false,
            keepAll: false,
            reportDir: 'coverage', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: ''])
}