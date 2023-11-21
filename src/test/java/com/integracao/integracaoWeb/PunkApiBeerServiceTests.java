package com.integracao.integracaoWeb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integracao.integracaoWeb.client.PunkApiBeerClient;
import com.integracao.integracaoWeb.response.PunkApiBeerResponse;
import com.integracao.integracaoWeb.service.PunkApiBeerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class PunkApiBeerServiceTests {

    @Mock
    PunkApiBeerClient punkApiBeerClient;

    @InjectMocks
    PunkApiBeerService punkApiBeerService;

    @Test
    @DisplayName("Sould return the first 25 beers of the api")
    public void getAllBeers() throws JsonProcessingException {
        doReturn(getListByPredicate(e -> e !=  null)).when(punkApiBeerClient).getAllBeers();
        List<PunkApiBeerResponse> beersbyABVRange = punkApiBeerService.getAllBeers();
        Assertions.assertEquals(25,beersbyABVRange.size());
    }

    @Test
    @DisplayName("Should return list with beer according to min and max ABV passed by params")
    void test_getBeersbyABVRange() throws JsonProcessingException {
        doReturn(getListByPredicate(e -> e.getAbv() == 4)).when(punkApiBeerClient).getBeersbyABVRange(4,5);
        List<PunkApiBeerResponse> beersbyABVRange = punkApiBeerService.getBeersbyABVRange(4, 5);
        Assertions.assertEquals(8,beersbyABVRange.size());
    }

    @Test
    @DisplayName("Should return list with beer according to min and max IBU passed by params")
    void test_getBeersbyIBURange() throws JsonProcessingException {
        doReturn(getListByPredicate(e -> e.getIbu() == 35)).when(punkApiBeerClient).getBeersbyIBURange(34,36);
        List<PunkApiBeerResponse> result = punkApiBeerService.getBeersbyIBURange(34, 36);
        Assertions.assertEquals(1,result.size());
    }


    @Test
    @DisplayName("Should return list with beer according to min and max EBC passed by params")
    void test_getBeersbyEBCRange() throws JsonProcessingException {
        doReturn(getListByPredicate(e -> e.getEbc() > 14 && e.getEbc() <20)).when(punkApiBeerClient).getBeersbyEBCRange(14,20);
        List<PunkApiBeerResponse> result = punkApiBeerService.getBeersbyEBCRange(14, 20);
        Assertions.assertEquals(2,result.size());
    }



    private List<PunkApiBeerResponse> mockarCervejas() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PunkApiBeerResponse>> listType = new TypeReference<List<PunkApiBeerResponse>>() {};
        return objectMapper.readValue(getJsonCervejasPage25Cervejas(), listType);
    }

    private List<PunkApiBeerResponse> getListByPredicate(Predicate<PunkApiBeerResponse> predicate) throws JsonProcessingException {
        return  mockarCervejas()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }


    private String getJsonCervejasPage25Cervejas(){
       return "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Buzz\",\n" +
                "    \"description\": \"A light, crisp and bitter IPA brewed with English and American hops. A small batch brewed only once.\",\n" +
                "    \"abv\": 4,\n" +
                "    \"ibu\": 60,\n" +
                "    \"ebc\": 20\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"Trashy Blonde\",\n" +
                "    \"description\": \"A titillating, neurotic, peroxide punk of a Pale Ale. Combining attitude, style, substance, and a little bit of low self esteem for good measure; what would your mother say? The seductive lure of the sassy passion fruit hop proves too much to resist. All that is even before we get onto the fact that there are no additives, preservatives, pasteurization or strings attached. All wrapped up with the customary BrewDog bite and imaginative twist.\",\n" +
                "    \"abv\": 4,\n" +
                "    \"ibu\": 41,\n" +
                "    \"ebc\": 15\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 3,\n" +
                "    \"name\": \"Berliner Weisse With Yuzu - B-Sides\",\n" +
                "    \"description\": \"Japanese citrus fruit intensifies the sour nature of this German classic.\",\n" +
                "    \"abv\": 4,\n" +
                "    \"ibu\": 8,\n" +
                "    \"ebc\": 8\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 4,\n" +
                "    \"name\": \"Pilsen Lager\",\n" +
                "    \"description\": \"Our Unleash the Yeast series was an epic experiment into the differences in aroma and flavour provided by switching up your yeast. We brewed up a wort with a light caramel note and some toasty biscuit flavour, and hopped it with Amarillo and Centennial for a citrusy bitterness. Everything else is down to the yeast. Pilsner yeast ferments with no fruity esters or spicy phenols, although it can add a hint of butterscotch.\",\n" +
                "    \"abv\": 6,\n" +
                "    \"ibu\": 55,\n" +
                "    \"ebc\": 30\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 5,\n" +
                "    \"name\": \"Avery Brown Dredge\",\n" +
                "    \"description\": \"An Imperial Pilsner in collaboration with beer writers. Tradition. Homage. Revolution. We wanted to showcase the awesome backbone of the Czech brewing tradition, the noble Saaz hop, and also tip our hats to the modern beers that rock our world, and the people who make them.\",\n" +
                "    \"abv\": 7,\n" +
                "    \"ibu\": 59,\n" +
                "    \"ebc\": 10\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 6,\n" +
                "    \"name\": \"Electric India\",\n" +
                "    \"description\": \"Re-brewed as a spring seasonal, this beer – which appeared originally as an Equity Punk shareholder creation – retains its trademark spicy, fruity edge. A perfect blend of Belgian Saison and US IPA, crushed peppercorns and heather honey are also added to produce a genuinely unique beer.\",\n" +
                "    \"abv\": 5,\n" +
                "    \"ibu\": 38,\n" +
                "    \"ebc\": 15\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 7,\n" +
                "    \"name\": \"AB:12\",\n" +
                "    \"description\": \"An Imperial Black Belgian Ale aged in old Invergordon Scotch whisky barrels with mountains of raspberries, tayberries and blackberries in each cask. Decadent but light and dry, this beer would make a fantastic base for ageing on pretty much any dark fruit - we used raspberries, tayberries and blackberries beause they were local.\",\n" +
                "    \"abv\": 11,\n" +
                "    \"ibu\": 35,\n" +
                "    \"ebc\": 80\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 8,\n" +
                "    \"name\": \"Fake Lager\",\n" +
                "    \"description\": \"Fake is the new black. Fake is where it is at. Fake Art, fake brands, fake breasts, and fake lager. We want to play our part in the ugly fallout from the Lager Dream. Say hello to Fake Lager – a zesty, floral 21st century faux masterpiece with added BrewDog bitterness.\",\n" +
                "    \"abv\": 4,\n" +
                "    \"ibu\": 40,\n" +
                "    \"ebc\": 12\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 9,\n" +
                "    \"name\": \"AB:07\",\n" +
                "    \"description\": \"Whisky cask-aged imperial scotch ale. Beer perfect for when the rain is coming sideways. Liquorice, plum and raisin temper the warming alcohol, producing a beer capable of holding back the Scottish chill.\",\n" +
                "    \"abv\": 12,\n" +
                "    \"ibu\": 30,\n" +
                "    \"ebc\": 84\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 10,\n" +
                "    \"name\": \"Bramling X\",\n" +
                "    \"description\": \"Good old Bramling Cross is elegant, refined, assured, (boring) and understated. Understated that is unless you hop the living daylights out of a beer with it. This is Bramling Cross re-invented and re-imagined, and shows just what can be done with English hops if you use enough of them. Poor Bramling Cross normally gets lost in a woeful stream of conformist brown ales made by sleepy cask ale brewers. But not anymore. This beer shows that British hops do have some soul, and is a fruity riot of blackberries, pears, and plums. Reminds me of the bramble, apple and ginger jam my grandmother used to make.\",\n" +
                "    \"abv\": 7,\n" +
                "    \"ibu\": 75,\n" +
                "    \"ebc\": 22\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 11,\n" +
                "    \"name\": \"Misspent Youth\",\n" +
                "    \"description\": \"The brainchild of our small batch brewer, George Woods. A dangerously drinkable milk sugar- infused Scotch Ale.\",\n" +
                "    \"abv\": 7,\n" +
                "    \"ibu\": 30,\n" +
                "    \"ebc\": 120\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 12,\n" +
                "    \"name\": \"Arcade Nation\",\n" +
                "    \"description\": \"Running the knife-edge between an India Pale Ale and a Stout, this particular style is one we truly love. Black IPAs are a great showcase for the skill of our brew team, balancing so many complex and twisting flavours in the same moment. The citrus, mango and pine from the hops – three of our all-time favourites – play off against the roasty dryness from the malt bill at each and every turn.\",\n" +
                "    \"abv\": 5,\n" +
                "    \"ibu\": 60,\n" +
                "    \"ebc\": 200\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 13,\n" +
                "    \"name\": \"Movember\",\n" +
                "    \"description\": \"A deliciously robust, black malted beer with a decadent dark, dry cocoa flavour that provides an enticing backdrop to the Cascade hops.\",\n" +
                "    \"abv\": 4,\n" +
                "    \"ibu\": 50,\n" +
                "    \"ebc\": 140\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 14,\n" +
                "    \"name\": \"Alpha Dog\",\n" +
                "    \"description\": \"A fusion of caramel malt flavours and punchy New Zealand hops. A session beer you can get your teeth into.\",\n" +
                "    \"abv\": 4,\n" +
                "    \"ibu\": 42,\n" +
                "    \"ebc\": 62\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Mixtape 8\",\n" +
                "    \"description\": \"This recipe is for the Belgian Tripel base. A blend of two huge oak aged beers – half a hopped up Belgian Tripel, and half a Triple India Pale Ale. Both aged in single grain whisky barrels for two years and blended, each beer brings its own character to the mix. The Belgian Tripel comes loaded with complex spicy, fruity esters, and punchy citrus hop character.\",\n" +
                "    \"abv\": 14,\n" +
                "    \"ibu\": 50,\n" +
                "    \"ebc\": 40\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 16,\n" +
                "    \"name\": \"Libertine Porter\",\n" +
                "    \"description\": \"An avalanche of cross-continental hop varieties give this porter a complex spicy, resinous and citrusy aroma, with a huge malt bill providing a complex roasty counterpoint. Digging deeper into the flavour draws out cinder toffee, bitter chocolate and hints of woodsmoke.\",\n" +
                "    \"abv\": 6,\n" +
                "    \"ibu\": 45,\n" +
                "    \"ebc\": 219\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 17,\n" +
                "    \"name\": \"AB:06\",\n" +
                "    \"description\": \"Our sixth Abstrakt, this imperial black IPA combined dark malts with a monumental triple dry-hop, using an all-star team of some of our favourite American hops. Roasty and resinous.\",\n" +
                "    \"abv\": 11,\n" +
                "    \"ibu\": 150,\n" +
                "    \"ebc\": 70\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 18,\n" +
                "    \"name\": \"Russian Doll – India Pale Ale\",\n" +
                "    \"description\": \"The levels of hops vary throughout the range. We love hops, so all four beers are big, bitter badasses, but by tweaking the amount of each hop used later in the boil and during dry- hopping, we can balance the malty backbone with some unexpected flavours. Simcoe is used in the whirlpool for all four beers, and yet still lends different characters to each\",\n" +
                "    \"abv\": 6,\n" +
                "    \"ibu\": 70,\n" +
                "    \"ebc\": 25\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 19,\n" +
                "    \"name\": \"Hello My Name Is Mette-Marit\",\n" +
                "    \"description\": \"We sent this beer to Norway where it was known as 'Hello, my name is Censored’. You can make up your own mind as to why. This brew was a red berry explosion, with a reisnous bitter edge layered with dry berry tartness.\",\n" +
                "    \"abv\": 8,\n" +
                "    \"ibu\": 70,\n" +
                "    \"ebc\": 0\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 20,\n" +
                "    \"name\": \"Rabiator\",\n" +
                "    \"description\": \"Imperial Wheat beer / Weizenbock brewed by a homesick German in leather trousers. Think banana bread, bubble gum and David Hasselhoff.\",\n" +
                "    \"abv\": 10,\n" +
                "    \"ibu\": 26,\n" +
                "    \"ebc\": 24\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 21,\n" +
                "    \"name\": \"Vice Bier\",\n" +
                "    \"description\": \"Our take on the classic German Kristallweizen. A clear German wheat beer, layers of bubblegum and vanilla perfectly balanced with the American and New Zealand hops.\",\n" +
                "    \"abv\": 4,\n" +
                "    \"ibu\": 25,\n" +
                "    \"ebc\": 30\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 22,\n" +
                "    \"name\": \"Devine Rebel (w/ Mikkeller)\",\n" +
                "    \"description\": \"Two of Europe's most experimental, boundary-pushing brewers, BrewDog and Mikkeller, combined forces to produce a rebellious beer that combined their respective talents and brewing skills. The 12.5% Barley Wine fermented well, and the champagne yeast drew it ever closer to 12.5%. The beer was brewed with a single hop variety and was going to be partially aged in oak casks.\",\n" +
                "    \"abv\": 12,\n" +
                "    \"ibu\": 100,\n" +
                "    \"ebc\": 36\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 23,\n" +
                "    \"name\": \"Storm\",\n" +
                "    \"description\": \"Dark and powerful Islay magic infuses this tropical sensation of an IPA. Using the original Punk IPA as a base, we boosted the ABV to 8% giving it some extra backbone to stand up to the peated smoke imported directly from Islay.\",\n" +
                "    \"abv\": 8,\n" +
                "    \"ibu\": 60,\n" +
                "    \"ebc\": 12\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 24,\n" +
                "    \"name\": \"The End Of History\",\n" +
                "    \"description\": \"The End of History: The name derives from the famous work of philosopher Francis Fukuyama, this is to beer what democracy is to history. Complexity defined. Floral, grapefruit, caramel and cloves are intensified by boozy heat.\",\n" +
                "    \"abv\": 55,\n" +
                "    \"ibu\": 0,\n" +
                "    \"ebc\": 0\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 25,\n" +
                "    \"name\": \"Bad Pixie\",\n" +
                "    \"description\": \"2008 Prototype beer, a 4.7% wheat ale with crushed juniper berries and citrus peel.\",\n" +
                "    \"abv\": 4,\n" +
                "    \"ibu\": 45,\n" +
                "    \"ebc\": 8\n" +
                "  }\n" +
                "]";
    }
}
