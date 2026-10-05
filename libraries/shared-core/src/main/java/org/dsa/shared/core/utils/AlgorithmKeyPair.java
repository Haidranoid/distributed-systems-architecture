package org.dsa.shared.core.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import lombok.Getter;

@Getter
public class AlgorithmKeyPair {
  private final SignatureAlgorithm signatureAlgorithm;
  private final PrivateKey privateKey;
  private final PublicKey publicKey;

  public AlgorithmKeyPair(
      String signatureAlgorithmPlain, String privateKeyPlain, String publicKeyPlain)
      throws Exception {
    this.signatureAlgorithm = this.generateSignatureAlgorithm(signatureAlgorithmPlain);
    this.privateKey = this.generatePrivateKey(privateKeyPlain);
    this.publicKey = this.generatePublicKey(publicKeyPlain);
  }

  private SignatureAlgorithm generateSignatureAlgorithm(String signatureAlgorithmPlain) {
    return (SignatureAlgorithm) Jwts.SIG.get().get(signatureAlgorithmPlain);
  }

  private PrivateKey generatePrivateKey(String privateKeyPlain) throws Exception {
    byte[] decoded = decode(privateKeyPlain);

    return keyFactory().generatePrivate(new PKCS8EncodedKeySpec(decoded));
  }

  private PublicKey generatePublicKey(String publicKeyPlain) throws Exception {
    byte[] decoded = decode(publicKeyPlain);

    return keyFactory().generatePublic(new X509EncodedKeySpec(decoded));
  }

  private KeyFactory keyFactory() throws Exception {
    String jcaName = signatureAlgorithm.getId(); // RS256, ES256, etc.
    String keyType = jcaName.startsWith("ES") ? "EC" : "RSA";

    return KeyFactory.getInstance(keyType);
  }

  // PEM/Base64
  private byte[] decode(String key) {
    return Base64.getDecoder()
        .decode(
            key.replaceAll("-----BEGIN(.*?)-----", "")
                .replaceAll("-----END(.*?)-----", "")
                .replaceAll("\\s", ""));
  }
}
