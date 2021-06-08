package com.naiofy.albums_shop.questions.buy_album;

import com.naiofy.albums_shop.actions.purchased_album_list.PurchasedAlbumListActions;
import com.naiofy.albums_shop.utils.Dictionary;
import net.thucydides.core.annotations.Steps;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BuyAlbumQuestions {
    @Steps
    PurchasedAlbumListActions purchasedAlbumListActions;

    public void verifyPurchase(Map<String, String> loginInfo, int albumIdPurchased, int userIdAlbum) {
        purchasedAlbumListActions.seePurchasedAlbums(loginInfo);

        assertThat(userIdAlbum).isEqualTo(lastResponse().getBody().path("[0].album.user_id"));
        assertThat(albumIdPurchased).isEqualTo(lastResponse().getBody().path("[0].album.id"));
        assertThat("quidem molestiae enim").isEqualTo(lastResponse().getBody().path("[0].album.title"));
        assertThat(loginInfo.get(Dictionary.USER_ID_TAG)).isEqualTo(lastResponse().getBody().path("[0].user_id"));
    }
}
