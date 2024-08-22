package com.narotam.webapp;

import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.s3.BlockPublicAccess;

public class WebAppStack extends Stack {

    public WebAppStack(final App scope, final String id) {
        this(scope, id, null);
    }
    public WebAppStack(final App scope, final String id, final StackProps props) {
        super(scope, id, props);

        Bucket gtdWebAppBucket = Bucket.Builder.create(this, "gtdWebAppBucket")
            .bucketName("gtd-web-app-bucket") // Customize your bucket name
            .websiteIndexDocument("index.html")
            .websiteErrorDocument("error.html")
            .publicReadAccess(true)  // Allow public access for website hosting
            .blockPublicAccess(BlockPublicAccess.BLOCK_ACLS)  // Only for specific ACLs
            .build();
    }

    public static void main(final String[] args) {
        App app = new App();

        new WebAppStack(app, "gtd-webapp", StackProps.builder().stackName("gtd-webapp").build());

        app.synth();
    }
}
