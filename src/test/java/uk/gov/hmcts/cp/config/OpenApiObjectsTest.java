package uk.gov.hmcts.cp.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import uk.gov.hmcts.cp.openapi.api.HearingResultsApi;
import uk.gov.hmcts.cp.openapi.model.ErrorResponse;
import uk.gov.hmcts.cp.openapi.model.HearingResultView;
import uk.gov.hmcts.cp.openapi.model.OffenceResult;
import uk.gov.hmcts.cp.openapi.model.Sentence;
import java.lang.reflect.Field;
import java.time.Instant;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class OpenApiObjectsTest {
    @Test
    void generated_error_response_should_have_expected_fields() {
        assertThat(ErrorResponse.class).hasDeclaredMethods("error", "message", "details", "traceId");
    }

    @Test
    void generated_hearing_result_view_should_have_expected_fields() {
        assertThat(HearingResultView.class)
                .hasDeclaredFields("id", "pcr", "offences", "courtApplicationResults");
    }

    @Test
    void generated_offence_result_should_have_expected_fields() {
        assertThat(OffenceResult.class).hasDeclaredFields("id", "code", "result");
    }

    @Test
    void generated_sentence_should_have_expected_fields() {
        assertThat(Sentence.class)
                .hasDeclaredFields("id", "type", "custodyStatus", "financialAmount", "periodLengths", "pcr");
    }

    @Test
    void generated_hearing_results_api_should_have_expected_methods() {
        assertThat(HearingResultsApi.class).hasDeclaredMethods("getHearingResult");
    }

    @Test
    void generated_error_response_timestamp_should_be_instant() throws Exception {
        Field timestampField = ErrorResponse.class.getDeclaredField("timestamp");

        assertThat(timestampField.getType())
                .as("timestamp field type")
                .isEqualTo(Instant.class);
    }
}
