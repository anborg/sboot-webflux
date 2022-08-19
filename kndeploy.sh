source .env.sh
envsubst < kn-app-deploy.yaml | oc apply -f -

watch kn service describe kn-$APP_NAME

