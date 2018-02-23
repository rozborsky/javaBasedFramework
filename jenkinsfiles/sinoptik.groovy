node ('master'){
    checkout scm

    bat "mvn clean install -Dconfig=sinoptik -Dtests=sinoptik.xml"

    publishHTML([
            allowMissing: false,
            alwaysLinkToLastBuild: false,
            keepAll: false,
            reportDir: 'target', reportFiles: 'Report*.html', reportName: 'HTML Report', reportTitles: ''])
}