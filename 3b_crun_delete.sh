source .env.sh
gcloud run services delete $APP_NAME --project=$GCP_PROJECT --region=$GCP_REGION
