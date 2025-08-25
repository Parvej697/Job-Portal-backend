package com.jobportal.Job.Portal.Utility;

public class Data {
    public static String getMessageBody(String otp , String name){

       return
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "  <meta charset=\"UTF-8\">\n" +
                        "  <title>Your OTP Code</title>\n" +
                        "</head>\n" +
                        "<body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 0; margin: 0;\">\n" +
                        "  <table align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width: 600px; background-color: #ffffff; margin-top: 20px; border-radius: 8px; overflow: hidden;\">\n" +
                        "    <tr>\n" +
                        "      <td style=\"background-color: #4CAF50; padding: 20px; text-align: center;\">\n" +
                        "        <h1 style=\"color: #ffffff; margin: 0; font-size: 24px;\">OTP Verification</h1>\n" +
                        "      </td>\n" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "      <td style=\"padding: 20px; color: #333333; font-size: 16px;\">\n" +
                        "        <p>Hi <strong>"+name+"</strong>,</p>\n" +
                        "        <p>Thank you for using our service. Your One-Time Password (OTP) for verification is:</p>\n" +
                        "        <p style=\"text-align: center; margin: 30px 0;\">\n" +
                        "          <span style=\"font-size: 32px; font-weight: bold; color: #4CAF50;\">"+otp+"</span>\n" +
                        "        </p>\n" +
                        "        <p>This OTP is valid for the next <strong>5 minutes</strong>. Please do not share it with anyone for security reasons.</p>\n" +
                        "        <p>If you did not request this OTP, please ignore this email.</p>\n" +
                        "      </td>\n" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "      <td style=\"background-color: #f4f4f4; text-align: center; padding: 15px; font-size: 12px; color: #777;\">\n" +
                        "        &copy; 2025 Job Portal App. All Rights Reserved.\n" +
                        "      </td>\n" +
                        "    </tr>\n" +
                        "  </table>\n" +
                        "</body>\n" +
                        "</html>";


    }
}
