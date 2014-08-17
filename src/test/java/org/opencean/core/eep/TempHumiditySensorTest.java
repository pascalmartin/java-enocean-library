package org.opencean.core.eep;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.opencean.core.address.EnoceanParameterAddress;
import org.opencean.core.common.EEPId;
import org.opencean.core.common.values.Value;
import org.opencean.core.packets.RadioPacket4BS;

public class TempHumiditySensorTest {

	@Test
    public void readPacketMax() {
        EEPParser sensor = new TempHumiditySensor(0, 40, 0, 100, EEPId.EEP_A5_04_01);
        RadioPacket4BS packet = new RadioPacket4BS();
        packet.setDb1((byte) 250);
        packet.setDb2((byte) 250);
        Map<EnoceanParameterAddress, Value> values = sensor.parsePacket(packet);
        assertEquals("size", 2, values.size());
        Iterator<Entry<EnoceanParameterAddress, Value>> iter = values.entrySet().iterator();
        assertEquals(new BigDecimal(100), iter.next().getValue().getValue());
        assertEquals(new BigDecimal(40), iter.next().getValue().getValue());
    }

    @Test
    public void readPacketMin() {
        EEPParser sensor = new TempHumiditySensor(0, 40, 0, 100, EEPId.EEP_A5_04_01);
        RadioPacket4BS packet = new RadioPacket4BS();
        packet.setDb1((byte) 0);
        packet.setDb2((byte) 0);
        Map<EnoceanParameterAddress, Value> values = sensor.parsePacket(packet);
        assertEquals("size", 2, values.size());
        Iterator<Entry<EnoceanParameterAddress, Value>> iter = values.entrySet().iterator();
        assertEquals(new BigDecimal(0), iter.next().getValue().getValue());
        assertEquals(new BigDecimal(0), iter.next().getValue().getValue());
        
    }

    @Test
    public void readPacket112() {
        EEPParser sensor = new TempHumiditySensor(0, 40, 0, 100, EEPId.EEP_A5_04_01);
        RadioPacket4BS packet = new RadioPacket4BS();
        packet.setDb1((byte) 112);
        packet.setDb2((byte) 112);
        Map<EnoceanParameterAddress, Value> values = sensor.parsePacket(packet);
        Iterator<Entry<EnoceanParameterAddress, Value>> iter = values.entrySet().iterator();
        assertEquals(new BigDecimal("44.8"), iter.next().getValue().getValue());
        assertEquals(new BigDecimal("17.9"), iter.next().getValue().getValue());
    }
    
}
