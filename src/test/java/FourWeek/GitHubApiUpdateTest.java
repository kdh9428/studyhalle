package FourWeek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHLabel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GitHub API를 이용해서")
class GitHubApiUpdateTest {

    GitHubApiUpdate api;
    String token;
    String repository;

    @BeforeEach
    void setUp() throws IOException {

        File tokenFile = new File("token.txt");
        List<String> fileLeaderList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(tokenFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileLeaderList.add(line);
            }

        token = fileLeaderList.get(0);
        repository = fileLeaderList.get(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        api = new GitHubApiUpdate(token, repository, "과제");

    }

    @Test
    @DisplayName("저장소를 가져오다.")
    void getRepositoryTest() throws IOException {
        GitHubApiUpdate api = new GitHubApiUpdate(token, repository, "과제");
        assertNotNull(api.getGitHubRepository());
    }

    @Test
    @DisplayName("가지고 온 저장소에서 이슈를 가져오다.")
    void getIssuesTest() throws IOException {
        List<GHIssue> issues = api.getGitHubRepository().getIssues(GHIssueState.ALL);
        assertNotNull(issues);
    }

    @Test
    @DisplayName("이슈가 과제인지 라벨로 확인한다. 라벨이 '과제'인 이슈의 수는 15")
    void separateIssuesWithLabelTest() throws IOException {
        String label = "과제";
        List<GHIssue> task = api.separateIssuesWithLabel();

        int count = 0;

        for (GHIssue issue : task) {
            for (GHLabel labels : issue.getLabels()) {
                if (labels.getName().equals(label)) {
                    count++;
                }
            }
        }
        assertEquals(15, task.size());
        assertEquals(15, count);
    }

    @Test
    @DisplayName("중복 제거된 참여자 아이디를 체크한다.")
    void getCommentsIdTest() throws IOException {
        Set<String> allCommentsUserId = api.getAllCommentsUserId();
        assertTrue(allCommentsUserId.contains("kdh9428"));
        for (String id : allCommentsUserId) {
            if (id.equals("kdh9428")) {

            }
        }
    }

    @Test
    @DisplayName("아이디 중복 제거, 총 참여 횟수 계산")
    void removeDuplicatesFromIssuesTest() throws IOException {
        Map<String, Integer> userMap = api.removeDuplicatesFromIssues();
        Set<String> keySet = userMap.keySet();
        Iterator<String> iterator = keySet.stream().iterator();
        String id = iterator.next();
        int count = 0;
        for (String userId : keySet) {
            if (userId.equals(id)) {
                count++;
            }
        }

        assertTrue(count == 1);

    }

    @Test
    @DisplayName("참여율 테스트, 100퍼센트 이상 또는 0퍼센트 이하일 경우 false")
    void participationRateTest() throws IOException {
        Map<String, Double> map = api.participationRate();
        for (String a : map.keySet()) {
            assertFalse(map.get(a) > 100 || map.get(a) < 0);
        }
    }
}