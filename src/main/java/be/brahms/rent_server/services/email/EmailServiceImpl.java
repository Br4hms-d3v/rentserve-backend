package be.brahms.rent_server.services.email;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing email
 * Import method EmailService
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    /**
     * Makes a new EmailServiceImpl.
     *
     * @param mailSender the mail sender.
     */
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends a confirmation email.
     *
     * @param to              The email to send to.
     * @param urlConfirmation The URL.
     */
    @Override
    public void sendMailConfirmation(String to, String urlConfirmation) {

        String htmlConfirmationHtml =
                "<html>" +
                        "<body>" +
                        "<h1>Merci pour votre inscription</h1>" +
                        "<p>Veuillez cliquer sur le lien ci-dessous pour confirmer votre compte :</p>" +
                        "<a href=\"" + urlConfirmation + "\">Confirmer mon inscription</a>" +
                        "</body>" +
                        "</html>";

        MimeMessage messageConfirmation = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(messageConfirmation, true);
            helper.setTo(to);
            helper.setSubject("Confirmation d'inscription");
            helper.setText(htmlConfirmationHtml, true);

            mailSender.send(messageConfirmation);
        } catch (Exception e) {
            System.out.println("erreur lors de l'envoie du mail: " + e.getMessage());
        }

    }

    /**
     * Send a confirmation email for update
     * if the user doesn't change password.
     * he clicks on the link to update
     *
     * @param to                    The email to send to.
     * @param urlWarnChangePassword The url to report that the password has been changed
     */
    @Override
    public void sendEmailUpdatePassword(String to, String urlWarnChangePassword) {

        String htmlWarPasswordUpdateHtml =
                "<html>" +
                        "<body>" +
                        "<h1>Changement du passe</h1>" +
                        "<p>Votre mot de passe à été mise à jour :</p>" +
                        "<p>Si ce n'est pas vous qui avez modifier votre mot de passe !</p>" +
                        "<p>Veuillez cliquez sur le lien et modifier votre mot de passe.</p>" +
                        "<a href=\"" + urlWarnChangePassword + "\">Changer mon mot de passe</a>" +
                        "</body>" +
                        "</html>";

        MimeMessage messageConfirmation = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(messageConfirmation, true);
            helper.setTo(to);
            helper.setSubject("Confirmation du changement du passe");
            helper.setText(htmlWarPasswordUpdateHtml, true);

            mailSender.send(messageConfirmation);
        } catch (Exception e) {
            System.out.println("erreur lors de l'envoie du mail: " + e.getMessage());
        }
    }

}
