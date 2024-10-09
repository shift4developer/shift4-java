package com.shift4;

import com.shift4.request.FileUploadListRequest;
import com.shift4.response.FileUpload;
import com.shift4.response.ListResponse;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import static com.shift4.enums.FileUploadPurpose.DISPUTE_EVIDENCE;
import static org.apache.commons.io.FileUtils.copyToFile;
import static org.assertj.core.api.Assertions.assertThat;

public class UploadsTest extends AbstractShift4GatewayTest {

    @Test
    void shouldUpload(@TempDir File tempDir) throws IOException {
        // given
        File tempFile = createTestFile(tempDir);
        // when
        FileUpload fileUpload = gateway.createFileUpload(tempFile, DISPUTE_EVIDENCE);
        // then
        assertThat(fileUpload.getId()).isNotBlank();
    }

    @Test
    void shouldUploadUsingExplicitMerchant(@TempDir File tempDir) throws IOException {
        // given
        Shift4Gateway gatewayWithExplicitMerchant = createExplicitMerchantGateway();
        File tempFile = createTestFile(tempDir);
        // when
        FileUpload fileUpload = gatewayWithExplicitMerchant.createFileUpload(tempFile, DISPUTE_EVIDENCE);
        // then
        assertThat(fileUpload.getId()).isNotBlank();
    }

    @Test
    void shouldRetrieveUploadedFile(@TempDir File tempDir) throws IOException {
        // given
        File tempFile = createTestFile(tempDir);
        FileUpload created = gateway.createFileUpload(tempFile, DISPUTE_EVIDENCE);
        // when
        FileUpload retrieved = gateway.retrieveFileUpload(created.getId());
        // then
        assertThat(retrieved.getId())
                .isEqualTo(created.getId())
                .matches("file_\\w+");
    }

    @Test
    void shouldListUploadedFile(@TempDir File tempDir) throws IOException {
        // given
        File tempFile = createTestFile(tempDir);
        FileUpload created = gateway.createFileUpload(tempFile, DISPUTE_EVIDENCE);
        // when
        ListResponse<FileUpload> listResponse= gateway.listFileUploads(new FileUploadListRequest().limit(100));
        // then
        assertThat(listResponse).anyMatch(item -> item.getId().equals(created.getId()));
    }

    private static File createTestFile(File tempDir) throws IOException {
        File tempFile = new File(tempDir, "testFile.pdf");
        copyToFile(new ByteArrayInputStream(RandomUtils.nextBytes(10)), tempFile);
        return tempFile;
    }
}
