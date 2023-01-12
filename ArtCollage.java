/*************************************************************************
 *  Compilation:  javac ArtCollage.java
 *  Execution:    java ArtCollage Flo2.jpeg
 *
 *  @author: Mohammad Sohail / mms458@scarletmail.rutgers.edu / mms458
 *
 *************************************************************************/

import java.awt.Color;

public class ArtCollage {

    // The orginal picture
    private Picture original;

    // The collage picture
    private Picture collage;

    // The collage Picture consists of collageDimension X collageDimension tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    private int tileDimension;
    
    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 100
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename) 
    {
        collageDimension = 4;
        tileDimension = 100;
        original = new Picture(filename);
        collage = new Picture(tileDimension*collageDimension,tileDimension*collageDimension);
        int w = collage.width();
        int h = collage.height();
        for(int tcol = 0; tcol < w; tcol++) 
        {
            for(int trow = 0; trow < h; trow++) 
            {
                int scol = tcol * original.width() / w; 
				int srow = trow * original.height() / h; 
				Color color = original.get(scol, srow);
				collage.set(tcol, trow, color);  
			}
		} 
	    // WRITE YOUR CODE HERE
    }
	
    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename, int td, int cd) 
    {
        collageDimension = cd;
        tileDimension = td;
        original = new Picture(filename);
        collage = new Picture(tileDimension*collageDimension,tileDimension*collageDimension);
        int w = collage.width();
        int h = collage.height();
        for(int tcol = 0; tcol < w; tcol++) 
        {
            for(int trow = 0; trow < h; trow++) 
            {
                int scol = tcol * original.width() / w; 
				int srow = trow * original.height() / h; 
				Color color = original.get(scol, srow);
				collage.set(tcol, trow, color);  
			}
		} 
	    // WRITE YOUR CODE HERE
    }

    /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */
    public int getCollageDimension() 
    {
        return collageDimension;
	    // WRITE YOUR CODE HERE
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */
    public int getTileDimension() 
    {
        return tileDimension;
	    // WRITE YOUR CODE HERE
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    public Picture getOriginalPicture() 
    {
        return original;
	    // WRITE YOUR CODE HERE
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    public Picture getCollagePicture() 
    {
        return collage;
	    // WRITE YOUR CODE HERE
    }
    
    /*
     * Display the original image
     * Assumes that original has been initialized
     */
    public void showOriginalPicture() 
    {
        original.show();

	    // WRITE YOUR CODE HERE
    }

    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */
    public void showCollagePicture() 
    {
        collage.show();
	    // WRITE YOUR CODE HERE
    }

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) 
    {
        Picture replace = new Picture(filename);
        for(int cCol = 0; cCol < tileDimension; cCol++)
        {
            for(int cRow = 0; cRow < tileDimension; cRow++)
            {
                int originalCol = cCol * replace.width() / tileDimension;
                int originalRow = cRow * replace.height() / tileDimension;
                Color color = replace.get(originalCol, originalRow);
                replace.set(cCol, cRow, color);
            }
        }
        for(int tileRow = 0; tileRow < collageDimension; tileRow++)
        {
            for(int tileCol = 0; tileCol < collageDimension; tileCol++)
            {
                if(tileRow == collageRow && tileCol == collageCol)
                {
                    for(int dX = 0; dX < tileDimension; dX++)
                    {
                        for(int dY = 0; dY < tileDimension; dY++)
                        {
                            Color color2 = replace.get(dY, dX);
                            this.collage.set(dY + (tileCol * tileDimension), dX + (tileRow * tileDimension), color2);
                        }
                    }
                }
            }
        }
	    // WRITE YOUR CODE HERE
    }
    
    /*
     * Makes a collage of tiles from original Picture
     * original will have collageDimension x collageDimension tiles, each tile
     * has tileDimension X tileDimension pixels
     */
    public void makeCollage () 
    {
        Picture newPic = new Picture (tileDimension,tileDimension);
        for(int collageCol = 0; collageCol < tileDimension; collageCol++)
        {
            for(int collageRow = 0; collageRow < tileDimension; collageRow++)
            {
                int originalCol = collageCol * original.width() / tileDimension;
                int originalRow = collageRow * original.height() / tileDimension;
                Color color = original.get(originalCol, originalRow);
                newPic.set(collageCol, collageRow, color);
            }
        }
        for(int tileRow = 0; tileRow < collageDimension; tileRow++)
        {
            for(int tileCol = 0; tileCol < collageDimension; tileCol++)
            {
                for(int dX = 0; dX < tileDimension; dX++)
                {
                    for(int dY = 0; dY < tileDimension; dY++)
                    {
                        Color color2 = newPic.get(dY, dX);
                        this.collage.set(dY + (tileCol * tileDimension), dX + (tileRow * tileDimension), color2);
                    }
                }
            }
        }
           
	    // WRITE YOUR CODE HERE
    }

    /*
     * Colorizes the tile at (collageCol, collageRow) with component 
     * (see CS111 Week 9 slides, the code for color separation is at the 
     *  book's website)
     *
     * @param component is either red, blue or green
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void colorizeTile (String component,  int collageCol, int collageRow) 
    {
        for(int tileRow = 0; tileRow < collageDimension; tileRow++)
        {
            for(int tileCol = 0; tileCol < collageDimension; tileCol++)
            {
                if(tileRow == collageRow && tileCol == collageCol)
                {
                    for(int dX = 0; dX < tileDimension; dX++)
                    {
                        for(int dY = 0; dY < tileDimension; dY++)
                        {
                            Color color = collage.get(dY + (tileCol * tileDimension), dX + (tileRow * tileDimension));
                            Color color2 = new Color(0, 0, 0);
                            int r = color.getRed();
                            int g = color.getGreen();
                            int b = color.getBlue();
                            // int kL = (int) Luminance.intensity(color);
                            if(component.equals("red"))
                            {
                                color2 = new Color(r, 0, 0);
                            }
                            else if(component.equals("blue"))
                            {
                                color2 = new Color(0, 0, b);
                            }
                            else if(component.equals("green"))
                            {
                                color2 = new Color(0, g, 0);
                            }
                            // Color color2 = new Color(kL, 0, 0);
                            // Color = new Color (rL, gL, bL);
                            // this.collage.set(resY + (tileCol * tileDimension), resX + (tileRow * tileDimension), color2);
                            this.collage.set(dY + (tileCol * tileDimension), dX + (tileRow * tileDimension), color2);
                        }
                    } 
                }
            }
        }

	    // WRITE YOUR CODE HERE
    }

    /*
     * Grayscale tile at (collageCol, collageRow)
     * (see CS111 Week 9 slides, the code for luminance is at the book's website)
     *
     * @param collageCol tile column
     * @param collageRow tile row
     */

    public void grayscaleTile (int collageCol, int collageRow) 
    {
        for(int tileRow = 0; tileRow < collageDimension; tileRow++)
        {
            for(int tileCol = 0; tileCol < collageDimension; tileCol++)
            {
                if(tileRow == collageRow && tileCol == collageCol)
                {
                    for(int dX = 0; dX < tileDimension; dX++)
                    {
                        for(int dY = 0; dY < tileDimension; dY++)
                        {
                            Color color = collage.get(dY + (tileCol * tileDimension), dX + (tileRow * tileDimension));
                            Color cL = Luminance.toGray(color);
                            
                            this.collage.set(dY + (tileCol * tileDimension), dX + (tileRow * tileDimension), cL);
                        }
                    }
                } 
            }
        }
	    // WRITE YOUR CODE HERE
    }


    /*
     *
     *  Test client: use the examples given on the assignment description to test your ArtCollage
     */
    public static void main (String[] args) {
        ArtCollage art = new ArtCollage(args[0], 200, 2); 
        // ArtCollage art3 = new ArtCollage(args[0], 200, 3);
        // ArtCollage art2 = new ArtCollage(args[0]);
        art.makeCollage();
        // art2.makeCollage();
        // art3.makeCollage();
        art.replaceTile(args[0], 0, 0);
        art.replaceTile(args[1], 0, 1);
        art.replaceTile(args[2], 1, 0);
        art.replaceTile(args[3], 1, 1);
        art.colorizeTile("blue", 1, 0);
        // art2.colorizeTile("red", 0, 0);
        // art3.colorizeTile("red", 1, 2);
        art.grayscaleTile(0,1);
        art.showCollagePicture();
        

    
    }
}
