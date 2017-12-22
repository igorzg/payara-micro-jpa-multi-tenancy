package icoder.helpers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import icoder.providers.JsonProvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Json mapper
 *
 * @since 1.0
 */
public class Json {


  public static ObjectMapper mapper() {
    return JsonProvider.mapper();
  }

  private static String generateJson(Object o, boolean prettyPrint, boolean escapeNonASCII) {
    try {
      ObjectWriter writer = mapper().writer();
      if (prettyPrint) {
        writer = writer.with(SerializationFeature.INDENT_OUTPUT);
      }
      if (escapeNonASCII) {
        writer = writer.with(JsonGenerator.Feature.ESCAPE_NON_ASCII);
      }
      return writer.writeValueAsString(o);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Convert input stream to type
   */
  public static <T> T fromObject(String data, Class<T> type) {
    return fromJson(parse(data), type);
  }

  /**
   * Convert input stream to type
   */
  public static <T> T fromObject(Object data, Class<T> type) {
    return fromJson(toJson(data), type);
  }

  /**
   * Convert input stream to type
   *
   * @param inputStream input stream
   */
  public static <T> T fromStream(InputStream inputStream, Class<T> type) {
    try {
      return mapper().readValue(new InputStreamReader(inputStream, Charset.forName("UTF-8")), type);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Convert an object to string.
   *
   * @param data object to convert to string
   */
  public static String toString(final Object data) {
    return generateJson(data, false, false);
  }

  /**
   * Convert an object to JsonNode.
   *
   * @param data Value to convert in Json.
   */
  public static JsonNode toJson(final Object data) {
    try {
      return mapper().valueToTree(data);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Convert a JsonNode to a Java value
   *
   * @param json  Json value to convert.
   * @param clazz Expected Java value type.
   */
  public static <A> A fromJson(JsonNode json, Class<A> clazz) {
    try {
      return mapper().treeToValue(json, clazz);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Creates a new empty ObjectNode.
   */
  public static ObjectNode newObject() {
    return mapper().createObjectNode();
  }

  /**
   * Creates a new empty ArrayNode.
   */
  public static ArrayNode newArray() {
    return mapper().createArrayNode();
  }

  /**
   * Convert a JsonNode to its string representation.
   */
  public static String stringify(JsonNode json) {
    return generateJson(json, false, false);
  }

  /**
   * Convert a JsonNode to its string representation, escaping non-ascii characters.
   */
  public static String asciiStringify(JsonNode json) {
    return generateJson(json, false, true);
  }

  /**
   * Convert a JsonNode to its string representation.
   */
  public static String prettyPrint(JsonNode json) {
    return generateJson(json, true, false);
  }

  /**
   * Parse a String representing a json, and return it as a JsonNode.
   */
  public static JsonNode parse(String src) {
    try {
      return mapper().readTree(src);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  /**
   * Parse a InputStream representing a json, and return it as a JsonNode.
   */
  public static JsonNode parse(java.io.InputStream src) {
    try {
      return mapper().readTree(src);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  /**
   * Parse a byte array representing a json, and return it as a JsonNode.
   */
  public static JsonNode parse(byte[] src) {
    try {
      return mapper().readTree(src);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

}
