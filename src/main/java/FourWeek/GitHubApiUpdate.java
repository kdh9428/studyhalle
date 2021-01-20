package FourWeek;

import org.kohsuke.github.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class GitHubApiUpdate {

    private GitHub gitHub;
    private String repository;
    private int numberOfIssue;
    private Map<String, Integer> participant;
    private String label;

    public GitHubApiUpdate(String token, String repository, String label) throws IOException {
        this.gitHub = new GitHubBuilder().withOAuthToken(token).build();
        this.repository = repository;
        this.label = label;
    }

    public GHRepository getGitHubRepository() throws IOException {
        return gitHub.getRepository(repository);
    }

    public List<GHIssue> separateIssuesWithLabel() throws IOException {

        //모든 이슈를 가져온다.
        List<GHIssue> issues = getGitHubRepository().getIssues(GHIssueState.ALL);

        List<GHIssue> listWithLabel = new ArrayList<>();
        for (GHIssue issue : issues) { //이슈를 순회하면서
            for (GHLabel label : issue.getLabels()) { //라벨을 가져온다.
                if (label.getName().equals(this.label)) //라벨의 이름이 같다면
                    listWithLabel.add(issue);
            }
        }
        numberOfIssue = listWithLabel.size();
        return listWithLabel;
    }

    //모든 이슈의 중복 제거된 아이디를 추출한다.
    public Set<String> getAllCommentsUserId() throws IOException {

        Set<String> id = new HashSet<>();

        for (GHIssue issue : separateIssuesWithLabel()) {

            for (GHIssueComment comment : issue.getComments()) {
                id.add(comment.getUser().getLogin());

            }
        }
        return id;
    }

    //참가자 중복 제거, 참여 횟수를 계산한다.
    public Map<String, Integer> removeDuplicatesFromIssues() throws IOException {
        List<GHIssue> issues = separateIssuesWithLabel();
        participant = new HashMap<>();

        for (GHIssue issue : issues){
        Set<String> deduplication = new HashSet<>();
            for (GHIssueComment comment : issue.getComments()){
                deduplication.add(comment.getUser().getLogin());
            }

            for (String id : deduplication){
                if (participant.containsKey(id)){
                    participant.replace(id, participant.get(id) + 1);
                    continue;
                }

                participant.put(id, 1);

            }
        }
        return participant;
    }

    //참여율 계산
    public Map<String, Double> participationRate() throws IOException {
        removeDuplicatesFromIssues();

        Map<String, Double> userMap = new HashMap<>();
        for (String id :participant.keySet()){
            BigDecimal percent = new BigDecimal((participant.get(id) / ((double) numberOfIssue)) * 100).setScale(2, RoundingMode.HALF_UP);
            double participationRate = percent.doubleValue();
            userMap.put(id, participationRate);
        }

        return userMap;
    }

    public void dashboard() throws IOException {

        Map<String, Double> participationRateMap = participationRate();
        for (String userId : participationRateMap.keySet()){
            System.out.println("참가자 : " + userId + ", 참여율 : " + participationRateMap.get(userId));
        }
    }
}