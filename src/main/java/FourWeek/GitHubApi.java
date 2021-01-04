package FourWeek;

import org.kohsuke.github.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class GitHubApi {

    private GitHub gitHub;
    private String repository;
    private int period;

    public void connect(String token, String repository) throws IOException {
        this.gitHub = new GitHubBuilder().withOAuthToken(token).build();
        this.repository = repository;
    }

    public GHRepository getGitHubRepository(String repositoryName) throws IOException {
        return gitHub.getRepository(repositoryName);
    }

    private List<GHIssue> issues() throws IOException {
        GHRepository ghRepository = getGitHubRepository(repository);
        List<GHIssue> issues = ghRepository.getIssues(GHIssueState.ALL);
        period = issues.size();
        return issues;
    }

    public Map<String, Set<String>> getParticipantListByIssue() throws IOException {
        List<GHIssue> issueComments = issues();
        Map<String, Set<String>> commentMap = new HashMap<>();
        for (GHIssue issue : issueComments){
            Set<String> login = new HashSet<>();
            for (GHIssueComment comment :issue.getComments()){
                login.add(comment.getUser().getLogin());
            }
            commentMap.put(issue.getTitle(), login);
        }
        return commentMap;
    }

    public List<String> getListOfAllParticipants() throws IOException {
        Map<String, Set<String>> list = getParticipantListByIssue();
        Set<String> keySet = list.keySet();

        Set<String> deduplication = new HashSet<>();
        for (String key : keySet){
            for (String id : list.get(key)){
                deduplication.add(id);
            }
        }
        return deduplication.stream().collect(Collectors.toList());
    }

    public Map<String, Double> getParticipantData() throws IOException {
        Map<String, Set<String>> setMap = getParticipantListByIssue();
        List<String> issueList = setMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());

        Map<String, Double> data = new HashMap<>();
        for (String list : getListOfAllParticipants()){
            double count = 0;
            for (String name : issueList){
                if (list.equals(name)){
                    ++count;
                }
            }
            data.put(list, count);
        }
        return data;
    }

    public void show() throws IOException {
        Map<String, Double> participantData = getParticipantData();
        participantData.keySet().stream().forEach(key -> System.out.println("아이디 : " + key + ", 참석률 : " + String.format("%.2f",(participantData.get(key) / (double) period) * 100)));
    }
}