package uy.edu.um.prog2.tad.f1;

import lombok.*;

@RequiredArgsConstructor
@Getter
@ToString
public class UserCounter {
    @NonNull String userName;
    @NonNull long counter;
    @NonNull boolean verified;

    public void increment() {
        counter++;
    }
}
