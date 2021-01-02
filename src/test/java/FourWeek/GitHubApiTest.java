package FourWeek;

import org.junit.jupiter.api.*;
import org.kohsuke.github.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class GitHubApiTest {

    GitHubApi githubApi;

    @BeforeEach
    @DisplayName("GitHub 연결")
    public void githubConnectionTest() {
        githubApi = new GitHubApi();

        File file = new File(".\\token.txt");

        StringBuffer buffer = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
//            githubApi.githubConnect(buffer.toString(), "whiteship/live-study");
            githubApi.connect(buffer.toString(), "kdh9428/AgileJava");
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    @DisplayName("GitHub 저장소 가져오기")
    public void getRepository() throws IOException {
        GHRepository gitHubRepository = githubApi.getGitHubRepository("kdh9428/AgileJava");
        assertNotNull(gitHubRepository);

    }

    @Test
    @DisplayName("중복 제거된 참가자 명단")
    public void testIssueIdList() throws IOException {
        List<String> listOfAllParticipants = githubApi.getListOfAllParticipants();

        assertNotNull(listOfAllParticipants.size());

        System.out.println("중복 제거 명단");
        listOfAllParticipants.stream().forEach(System.out::println);
    }

    @Test
    @DisplayName("각각의 이슈에서 유저 가져오기")
    public void testParticipantListByIssue() throws IOException {
        Map<String, Set<String>> list = githubApi.getParticipantListByIssue();
        Set<String> keySet = list.keySet();
        keySet.stream().map(key -> list.get(key)).flatMap(Collection::stream).collect(Collectors.toList()).stream().forEach(System.out::println);

    }

    @Test
    @DisplayName("참여자 데이터 보여주기")
    public void testCode() throws IOException {
        githubApi.show();
    }
}