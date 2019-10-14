package rest.server;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.Utgift;
import core.UtgiftList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rest.api.UtgiftListService;
import rest.api.UtgiftObjectMapperProvider;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.Arrays;

public class UtgiftListServiceTest extends UtgiftJerseyTest {

  private static final String UTGIFT_LIST_SERVICE_PATH = UtgiftListService.UTGIFT_LIST_SERVICE_PATH;

  private ObjectMapper objectMapper;

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    objectMapper = new UtgiftObjectMapperProvider().getContext(getClass());
  }

  @Test
  public void testPostGetPutGetDelete() throws Exception {
    // POST, i.e. add
   // final LatLong latLong = new LatLong(63, 11);
    final Utgift utgift = new Utgift("Fisk","200.0","Mat");
    final UtgiftList ul = new UtgiftList(Arrays.asList(utgift));

    final Response postResponse = target(UTGIFT_LIST_SERVICE_PATH)
        .request("application/json; charset=UTF-8").post(
                Entity.entity(objectMapper.writeValueAsString(ul),MediaType.APPLICATION_JSON));

        //.post(Entity.entity(objectMapper.writeValueAsString(new UtgiftList(Arrays.asList(utgift))), MediaType.APPLICATION_JSON));

    Assert.assertEquals(200, postResponse.getStatus());
    final Integer postNum = objectMapper.readValue(postResponse.readEntity(String.class), Integer.class);
    Assert.assertEquals(0, postNum.intValue());
    // GET
    testGet(0, utgift);

  }

  protected void doJsonOutput(final HttpURLConnection postCon, final Object content)
      throws IOException, JsonGenerationException, JsonMappingException {
    postCon.setDoOutput(true);
    final ByteArrayOutputStream out = new ByteArrayOutputStream();
    objectMapper.writeValue(out, content);
    out.close();
    final byte[] postBytes = out.toByteArray();
    postCon.setFixedLengthStreamingMode(postBytes.length);
    postCon.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    postCon.connect();
    try (OutputStream conOut = postCon.getOutputStream()) {
      conOut.write(postBytes);
    }
  }

  protected void testGet(final int num, final Utgift utgift)
      throws MalformedURLException, IOException, ProtocolException, JsonParseException, JsonMappingException {
    // GET
    final Response response = target(UTGIFT_LIST_SERVICE_PATH).path(String.valueOf(num))
        .request("application/json; charset=UTF-8")
        .get();
    Assert.assertEquals(200, response.getStatus());
    testContent(response.readEntity(String.class), utgift);
  }

  protected void testContent(final String content, final Utgift utgift)
      throws IOException, JsonParseException, JsonMappingException {
    final Utgift getUtgift = objectMapper.readValue(content, Utgift.class);
    Assert.assertEquals(utgift, getUtgift);
  }
}
