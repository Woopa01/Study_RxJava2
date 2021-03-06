import com.sun.javafx.tools.packager.Log;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class Operators {
    public static void main(String[] args) {

        //map 함수
        String[] balls = {"1","2","3","5"};
        Observable<String> source1 = Observable.fromArray(balls)
                .map(ball -> ball + "A");
        source1.subscribe(System.out::println);

        //Function 인터페이스 적용한 map 함수
        Function<String,String> getA = ball1 -> ball1 + "A";
        Observable<String> source2 = Observable.fromArray(balls)
                .map(getA);
        source2.subscribe(System.out::println);

        //데이터 타입 추론
        Function<String,Integer> ballToindex = ball2 -> {
            switch (ball2){
                case "RED" : return 1;
                case "YELLOW" : return 2;
                case "GREEN" : return 3;
                case "BLUE" : return 4;
                default: return -1;
            }
        };

        String[] balls2 = {"RED","YELLOW","GREEN","BLUE"};
        Observable<Integer> source3 = Observable.fromArray(balls2)
                .map(ballToindex);
        source3.subscribe(System.out::println);

        //flatMap 함수 활용
        Function<String,Observable<String>> getDoubleA =
                ball -> Observable.just(ball+"A",ball+"A");

        String[] balls3 = {"1","3","5"};

        Observable<String> source4 = Observable.fromArray(balls3)
                .flatMap(getDoubleA);
        source4.subscribe(System.out::println);

        //filter 함수 활용
        String[] objs = {"1 CIRCLE", "2 DIAMOND", "3 TRIANGLE",
                         "4 DIAMOND", "5 CIRCLE", "6 HEXAGON"};
        Observable<String> source5 = Observable.fromArray(objs)
                .filter(obj -> obj.endsWith("CIRCLE"));
        source5.subscribe(System.out::println);

        //reduce 함수 활용
        Maybe<String> source6 = Observable.fromArray(balls3)
                .reduce((ball1,ball2) -> ball2 + "(" + ball1 + ")");
        source6.subscribe(System.out::println);
    }
}
