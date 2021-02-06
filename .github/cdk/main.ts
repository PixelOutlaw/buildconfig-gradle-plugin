import { Construct } from "constructs";
import { App, Stack } from "cdkactions";
import {
  createGradlePluginPullRequestWorkflow,
  createGradlePluginPrepareForReleaseWorkflow,
  createGradlePluginReleaseWorkflow
} from "@pixeloutlaw/github-cdkactions";

export class MyStack extends Stack {
  constructor(scope: Construct, id: string) {
    super(scope, id);

    // define workflows here
    createGradlePluginPullRequestWorkflow(this, "plugin");
    createGradlePluginPrepareForReleaseWorkflow(this, "plugin");
    createGradlePluginReleaseWorkflow(this);
  }
}

const app = new App();
new MyStack(app, 'cdk');
app.synth();
