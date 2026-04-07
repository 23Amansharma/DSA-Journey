class Robot {
    private int w, h;
    private int perimeter;
    private int pos;
    private boolean hasMoved;

    public Robot(int width, int height) {
        this.w = width;
        this.h = height;
        // Calculate the total number of steps to make one full loop
        this.perimeter = 2 * (width + height - 2);
        this.pos = 0;
        this.hasMoved = false;
    }
    
    public void step(int num) {
        // Optimize massive steps using modulo
        pos = (pos + num) % perimeter;
        hasMoved = true; // Mark that the robot is no longer in its absolute initial state
    }
    
    public int[] getPos() {
        int w_edge = w - 1;
        int h_edge = h - 1;
        
        // Bottom Edge: Moving East
        if (pos <= w_edge) {
            return new int[]{pos, 0};
        }
        // Right Edge: Moving North
        else if (pos <= w_edge + h_edge) {
            return new int[]{w_edge, pos - w_edge};
        }
        // Top Edge: Moving West
        else if (pos <= 2 * w_edge + h_edge) {
            return new int[]{w_edge - (pos - (w_edge + h_edge)), h_edge};
        }
        // Left Edge: Moving South
        else {
            return new int[]{0, h_edge - (pos - (2 * w_edge + h_edge))};
        }
    }
    
    public String getDir() {
        // Edge case: at origin. Facing East if it never moved, otherwise South.
        if (pos == 0) {
            return hasMoved ? "South" : "East";
        }
        
        int w_edge = w - 1;
        int h_edge = h - 1;
        
        if (pos <= w_edge) return "East";
        if (pos <= w_edge + h_edge) return "North";
        if (pos <= 2 * w_edge + h_edge) return "West";
        return "South";
    }
}
