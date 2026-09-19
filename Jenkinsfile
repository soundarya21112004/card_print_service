pipeline {
    agent any

    environment {
        // Application
        WAR_FILE = 'target/PSA_PRINT.war'

        // Tomcat Server
        TOMCAT_HOST = '192.168.1.53'
        TOMCAT_USER = 'docker'
        TOMCAT_SERVICE = 'tomcat10'

        // Deployment
        DEPLOY_WAR = 'MVS.war'
        DEPLOY_SCRIPT = '/usr/local/bin/deploy-mvs.sh'

        // Jenkins SSH Key
        SSH_KEY = '/var/lib/jenkins/.ssh/id_ed25519'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out MVS source code...'

                git branch: 'main',
                    url: 'https://github.com/soundarya21112004/card_print_service.git'
            }
        }

        stage('Build WAR') {
            steps {
                echo 'Building war...'

                sh '''
                    set -e

                    export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
                    export PATH=$JAVA_HOME/bin:$PATH

                    echo "========================================="
                    echo "Java version:"
                    java -version

                    echo "========================================="
                    echo "Maven version:"
                    mvn -version

                    echo "========================================="
                    echo "Running Maven build with Java 11..."

                    mvn clean package
                '''
            }
        }

        stage('Verify WAR') {
            steps {
                echo 'Verifying generated WAR...'

                sh '''
                    set -e

                    if [ ! -f "${WAR_FILE}" ]; then
                        echo "ERROR: ${WAR_FILE} was not created!"
                        exit 1
                    fi

                    echo "WAR created successfully:"
                    ls -lh "${WAR_FILE}"
                '''
            }
        }

        stage('Archive WAR') {
            steps {
                echo 'Archiving WAR in Jenkins...'

                archiveArtifacts artifacts: 'target/PSA_PRINT.war',
                                 fingerprint: true
            }
        }

        stage('Test SSH Connection') {
            steps {
                echo 'Testing SSH connection to Tomcat server...'

                sh '''
                    set -e

                    ssh -i "${SSH_KEY}" \
                        -o BatchMode=yes \
                        -o StrictHostKeyChecking=no \
                        "${TOMCAT_USER}@${TOMCAT_HOST}" \
                        "hostname"
                '''
            }
        }

        stage('Test Tomcat Sudo Access') {
            steps {
                echo 'Testing restricted sudo access for Tomcat...'

                sh '''
                    set -e

                    echo "Checking Tomcat service status..."

                    ssh -i "${SSH_KEY}" \
                        -o BatchMode=yes \
                        -o StrictHostKeyChecking=no \
                        "${TOMCAT_USER}@${TOMCAT_HOST}" \
                        "sudo -n /usr/bin/systemctl status ${TOMCAT_SERVICE}"

                    echo "Tomcat sudo access verified successfully."
                '''
            }
        }

        stage('Copy WAR to Tomcat Server') {
            steps {
                echo 'Copying PSA_PRINT.war to Tomcat server...'

                sh '''
                    set -e

                    scp -i "${SSH_KEY}" \
                        -o BatchMode=yes \
                        -o StrictHostKeyChecking=no \
                        "${WAR_FILE}" \
                        "${TOMCAT_USER}@${TOMCAT_HOST}:/tmp/${DEPLOY_WAR}"

                    echo "WAR copied successfully."
                '''
            }
        }

        stage('Deploy MVS') {
            steps {
                echo 'Deploying PSA application...'

                sh '''
                    set -e

                    ssh -i "${SSH_KEY}" \
                        -o BatchMode=yes \
                        -o StrictHostKeyChecking=no \
                        "${TOMCAT_USER}@${TOMCAT_HOST}" \
                        "sudo -n ${DEPLOY_SCRIPT}"
                '''
            }
        }

        stage('Verify Tomcat') {
            steps {
                echo 'Verifying Tomcat service after deployment...'

                sh '''
                    set -e

                    ssh -i "${SSH_KEY}" \
                        -o BatchMode=yes \
                        -o StrictHostKeyChecking=no \
                        "${TOMCAT_USER}@${TOMCAT_HOST}" \
                        "sudo -n /usr/bin/systemctl status ${TOMCAT_SERVICE}"

                    echo "Tomcat is running successfully."
                '''
            }
        }
    }

    post {

        success {
            echo '''
=========================================
       PSA DEPLOYMENT SUCCESSFUL
=========================================

WAR       : target/PSA_PRINT.war
SERVER    : 192.168.1.53
USER      : docker
SERVICE   : tomcat10
TOMCAT    : /opt/tomcat10/apache-tomcat-10.1.57
APP       : PSA

=========================================
'''
        }

        failure {
            echo '''
=========================================
       PSA DEPLOYMENT FAILED
=========================================

Check Jenkins Console Output.

=========================================
'''
        }
    }
}
