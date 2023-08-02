import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List <Integer> result = solution.getPath(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        result.add(0, 1);
        System.out.println(result);
    }

    public List<Integer> getPath(int n, int m){
        return Stream.iterate(1, i -> i == n ? 1 : i + 1)
                .flatMap(takeNth(m - 1))
                .skip(1)
                .takeWhile(i -> i != 1)
                .collect(Collectors.toList());
    }

    private <T> Function<T, Stream<T>> takeNth(int n) {
        return new Function<T, Stream<T>>() {
            int i = 0;

            @Override
            public Stream<T> apply(T t) {
                if (i++ % n == 0) {
                    return Stream.of(t);
                }
                return Stream.empty();
            }
        };
    }
}
