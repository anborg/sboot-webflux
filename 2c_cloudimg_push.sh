source .env.sh
podman image tag $TAG_ORG  $TAG_GCP
gcloud auth print-access-token  --impersonate-service-account $DEV_SA  | podman login -u oauth2accesstoken --password-stdin us-central1-docker.pkg.dev
podman push $TAG_GCP

