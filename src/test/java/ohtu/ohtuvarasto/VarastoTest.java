package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttamineneEiOnnistu() {
        double otos = varasto.otaVarastosta(-1);
        assertEquals(0, otos, vertailuTarkkuus);
    }

    @Test
    public void liikaOttamistaEiSaaLiikaa() {
        varasto.lisaaVarastoon(8);
        double otos = varasto.otaVarastosta(10);

        assertEquals(8, otos, vertailuTarkkuus);
    }

    @Test
    public void kelvotonVarasto() {
        Varasto kelvoton = new Varasto(-2, -3);
        assertEquals(0, kelvoton.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, kelvoton.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiLisaaNegatiivista() {
        varasto.lisaaVarastoon(-9);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kelvotonVarasto2() {
        Varasto kelvoton = new Varasto(-2);
        assertEquals(0, kelvoton.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoSuurempiKuinTilavuus() {
        Varasto uusi = new Varasto(10, 20);
        assertEquals(uusi.getTilavuus(), uusi.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void hyväVarasto() {
        Varasto uusi = new Varasto(10, 9);
        uusi.lisaaVarastoon(3);
        assertEquals(10, uusi.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringTest() {
        varasto.toString();
    }
}
