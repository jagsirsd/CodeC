import java.util.*;

public class MainDS {

    public static void main(String[] args) {
        List<String> namesOfcandidates = new ArrayList<>(); //john Jane
        namesOfcandidates.add("John");
        namesOfcandidates.add("Jane");

        String[] rresults = getResults(namesOfcandidates);

    }

    static class NameToVote {
        final String name;
        Integer votes;


        public void setVotes(Integer votes) {
            this.votes = votes;
        }

        public Integer getVotes() {
            return votes;
        }

        public void setLastVoteIndex(int lastVoteIndex) {
            this.lastVoteIndex = lastVoteIndex;
        }

        Integer lastVoteIndex;

        NameToVote(String name, Integer votes) {
            this.name = name;
            this.votes = votes;
        }
    }
    public static String[] getResults(List<String> namesOfcandidates) {
        Map<String, NameToVote> votesCount = getStringNameToVoteMap(namesOfcandidates);

        NameToVote[] nameToVote = getNameToVotes(votesCount);

        return getStrings(nameToVote);
    }

    private static String[] getStrings(NameToVote[] nameToVote) {
        String[] sortedWinners = new String[nameToVote.length];
        int j = 0;
        for(NameToVote entry: nameToVote) {
            sortedWinners[j++] = entry.name;
        }
        return sortedWinners;
    }

    private static NameToVote[] getNameToVotes(Map<String, NameToVote> votesCount) {
        NameToVote[] nameToVote = new NameToVote[votesCount.size()];
        votesCount.values().toArray(nameToVote);

        Arrays.sort(nameToVote, (o1, o2) -> {
            int voteCompare = o2.votes.compareTo(o1.votes);

            if(voteCompare == 0 ) {
                return o1.lastVoteIndex.compareTo(o2.lastVoteIndex);
            } else {
                return voteCompare;
            }
        });
        return nameToVote;
    }

    private static Map<String, NameToVote> getStringNameToVoteMap(List<String> namesOfcandidates) {
        Map<String, NameToVote> votesCount = new HashMap<>();

        int voteOrder = 0;
        for (String name : namesOfcandidates) {
            voteOrder++;
            if (votesCount.containsKey(name)) {
                NameToVote prevCount = votesCount.get(name);
                prevCount.setVotes(prevCount.getVotes() + 1);
                prevCount.setLastVoteIndex(voteOrder);
                votesCount.put(name, prevCount);
            } else {
                NameToVote newVote = new NameToVote(name, 1);
                newVote.setLastVoteIndex(voteOrder);

                votesCount.put(name, newVote);
            }
        }
        return votesCount;
    }
}
