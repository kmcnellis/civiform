package views.admin.programs;

import static com.google.common.base.Preconditions.checkNotNull;
import static j2html.TagCreator.div;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.p;
import static j2html.TagCreator.span;
import static j2html.TagCreator.text;

import j2html.tags.specialized.DivTag;
import javax.inject.Inject;
import models.ApiKey;
import play.twirl.api.Content;
import views.BaseHtmlView;
import views.HtmlBundle;
import views.admin.AdminLayout;
import views.admin.AdminLayout.NavPage;
import views.admin.AdminLayoutFactory;

/** Renders a page that displays an API key's crentials after it's created. */
public final class ApiKeyCredentialsView extends BaseHtmlView {
  private final AdminLayout layout;

  private static final String CREDENTIALS_DESCRIPTION =
      "Please copy your API key and it store it somewhere secure. This is your only"
          + " opportunity to copy the secret from CiviForm, if you"
          + " refresh the page or navigate away you will not be able to recover the"
          + " secret value and will need to create a new key instead.";

  @Inject
  public ApiKeyCredentialsView(AdminLayoutFactory layoutFactory) {
    this.layout = checkNotNull(layoutFactory).getLayout(NavPage.API_KEYS);
  }

  public Content render(ApiKey apiKey, String credentials) {
    String title = "Created API key: " + apiKey.getName();

    DivTag contentDiv =
        div()
            .withClasses("px-20")
            .with(
                h1(title).withClasses("my-4"),
                h2("Credentials"),
                p(CREDENTIALS_DESCRIPTION),
                p(text("API key: "), span(credentials).withId("api-key-credentials"))
                    .withClasses("mt-4"));

    HtmlBundle htmlBundle = layout.getBundle().setTitle(title).addMainContent(contentDiv);

    return layout.renderCentered(htmlBundle);
  }
}
