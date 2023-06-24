package uy.edu.um.prog2.tad.f1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class UserFavourites {
    private String userName;
    private Integer favouritesNum;


    @Override
    public boolean equals(Object obj) {
        return ((UserFavourites) obj).userName.equals(this.userName);
    }

    public void add(int value) {
        favouritesNum += value;
    }
}
