package se.bjurr.prnfb.transformer;

import static com.atlassian.bitbucket.pull.PullRequestState.DECLINED;
import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static se.bjurr.prnfb.listener.PrnfbPullRequestAction.MERGED;
import static se.bjurr.prnfb.settings.PrnfbSettings.UNCHANGED;
import static se.bjurr.prnfb.test.Podam.populatedInstanceOf;
import static se.bjurr.prnfb.transformer.NotificationTransformer.toNotificationDto;
import static se.bjurr.prnfb.transformer.NotificationTransformer.toPrnfbNotification;

import com.google.common.collect.Lists;
import org.junit.Test;
import se.bjurr.prnfb.presentation.dto.NotificationDTO;
import se.bjurr.prnfb.settings.ValidationException;

public class NotificationTransformerTest {
  @Test
  public void testTransformation() throws ValidationException {
    NotificationDTO originalDto = populatedInstanceOf(NotificationDTO.class);
    originalDto.setUrl("http://hej.com/");
    originalDto.setTriggerIgnoreStateList(Lists.newArrayList(DECLINED.name()));
    originalDto.setTriggers(newArrayList(MERGED.name()));
    originalDto.setUser(UNCHANGED);
    originalDto.setPassword(UNCHANGED);
    originalDto.setProxyUser(UNCHANGED);
    originalDto.setProxyPassword(UNCHANGED);
    NotificationDTO retransformedDto = toNotificationDto(toPrnfbNotification(originalDto));

    assertThat(retransformedDto) //
        .isEqualTo(originalDto);
    assertThat(retransformedDto.toString()) //
        .isEqualTo(originalDto.toString());
    assertThat(retransformedDto.hashCode()) //
        .isEqualTo(originalDto.hashCode());
  }
}
