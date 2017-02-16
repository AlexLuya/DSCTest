/**
 * Copyright (c) (2010-2013),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The Class ImageUtil.
 *
 * @Author alex
 * @CreateTime Jul 5, 2016 10:08:02 PM
 * @Version 1.0
 * @Since 1.0
 */
public class ImageUtil
{

	/**
	 * Combine.
	 *
	 * @param image_1
	 *            the image 1
	 * @param image_2
	 *            the image 2
	 * @return the buffered image
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static BufferedImage combine(File image_1, File image_2) throws IOException
	{
		BufferedImage img1 = ImageIO.read(image_1);
		BufferedImage img2 = ImageIO.read(image_2);

		// do some calculate first
		int offset = 5;
		int wid = img1.getWidth() + img2.getWidth() + offset;
		int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;

		// create a new buffer and draw two image into the new image
		BufferedImage newImage = new BufferedImage(wid, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = newImage.createGraphics();
		Color oldColor = g2.getColor();

		// fill background
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, wid, height);

		// draw image
		g2.setColor(oldColor);
		g2.drawImage(img1, null, 0, 0);
		g2.drawImage(img2, null, img1.getWidth() + offset, 0);
		g2.dispose();

		return newImage;
	}

	/**
	 * Combine.
	 *
	 * @param image_1
	 *            the image 1
	 * @param image_2
	 *            the image 2
	 * @return the buffered image
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static BufferedImage combine(String image_1, String image_2) throws IOException
	{
		return combine(new File(image_1), new File(image_2));
	}

	/**
	 * Compare.
	 *
	 * @param image_1
	 *            the image 1
	 * @param image_2
	 *            the image 2
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static boolean compare(String image_1, String image_2) throws IOException{
		//HP
		return false;
	}
}