package com.android.supay.test.util;

import java.util.regex.Pattern;

/**
 *      Clase contenedora de
 * definiciones.
 *
 * @author Paulo_Angeles.
 */
public class Definitions {

    public static String SUPAY_GIF = "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/68ba9631906871.5945f9674252a.gif";

    public static String AVENGERS_MAPACHE = "Mapache";
    public static String AVENGERS_SPIDERMAN = "Spiderman";
    public static String AVENGERS_IRON_MAN = "Iron Man";

    public static String MESSAGE_ERROR_REQUIRED_FRASE = "Es necesario ingresar la frase del personaje.";
    public static String MESSAGE_ERROR_REQUIRED_EMAIL = "Es necesario ingresar el correo electrónico.";
    public static String MESSAGE_ERROR_REQUIRED_PASSWORD = "Es necesario ingresar la contraseña.";

    public static Long MIN_SIZE_PASSWORD = 2L;
    public static Long MAX_SIZE_PASSWORD = 8L;

    public static String KEY_PROFILE = "profile";
    public static String FRAGMENT_ARG_FLAG = "fragment_arg_flag";



    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public static String SRC_IMG_ADVENGERS_MAPACHE = "https://i1.wp.com/collectible506.com/wp-content/uploads/2018/03/20180309_100753.jpg?zoom=2.625&resize=412%2C228&ssl=1";
    public static String SRC_IMG_ADVENGERS_IRON_MAN = "http://mouse.latercera.com/wp-content/uploads/2017/10/avengers-ironman.jpg";
    public static String SRC_IMG_ADVENGERS_SPIDERMAN = "https://emptylighthouse-production.s3-us-west-2.amazonaws.com/s3fs-public/styles/728x_hero/public/field/image/30740878_10155432528682344_9083589659031764992_n.jpg?itok=-Jn0xdbw";
    public static String SRC_IMG_MAP_ADDRESS = "https://ep01.epimg.net/tecnologia/imagenes/2017/10/11/actualidad/1507722505_765166_1507722864_miniatura_normal.jpg";
}
