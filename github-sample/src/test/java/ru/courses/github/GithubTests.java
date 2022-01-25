package ru.courses.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("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");
        RepoCommits commits =  github.repos().get(new Coordinates.Simple("n-a-romanov","java-courses")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
