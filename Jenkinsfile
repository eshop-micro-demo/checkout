pipeline {
    agent any
    tools {
        maven "M3.8.1"
    }
    environment {
        // TERRAFORM_HOME = tool name: 'Terraform', type:'com.cloudbees.jenkins.plugins.customtools.CustomTool'
        KOPS_HOME = tool name: 'kops', type:'com.cloudbees.jenkins.plugins.customtools.CustomTool'
        KUBECTL_HOME = tool name: 'kubectl', type:'com.cloudbees.jenkins.plugins.customtools.CustomTool'
    }
    stages {
        stage('checkout_build') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'containerized', url:'https://github.com/eshop-micro-demo/checkout.git'
                sh "mvn -B -Pprod --no-transfer-progress -Dmaven.test.skip=true clean package jib:dockerBuild"
                sh """#! /bin/bash
                  # set -x
                  #ls -ltr
                  #which java
                  #java -version
                  #which mvn
                  #which docker

                  docker tag checkout:latest quay.io/microshop/checkout:latest
                  #docker image ls
                """
                script {
                    docker.withRegistry('https://quay.io', 'quay.io-login') {
                      def checkoutImg = docker.image('quay.io/microshop/checkout:latest')
                      checkoutImg.push()
                    }
                }
            }
        }
        stage('k8s_deploy') {
            steps {
                sh """#! /bin/bash
                  set -x
                  #ls -ltr
                  export PATH=$PATH:$KOPS_HOME:$KUBECTL_HOME
                  #which kubectl
                  #which kops
                  kops export kubecfg ${CLUSTER_NAME} --kubeconfig ./kops_kubeconfig --admin
                  KUBECONFIG=./kops_kubeconfig kubectl apply -n microshop -f k8s-deployment.yaml
                """
            }
        }
    }
}
