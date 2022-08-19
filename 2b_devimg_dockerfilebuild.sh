source .env.sh
podman build -t $TAG_ORG .
#podman image tag $IMG_LOCAL $TAG_ORG  $TAG_GCP
#mvn jib:build
#podman images
podman run  -ti --rm $TAG_ORG cat /etc/passwd
