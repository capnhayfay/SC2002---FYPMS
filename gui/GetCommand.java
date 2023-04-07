package gui;

/**
 * Represents command to get instruction from user and execute instruction
 */
public interface GetCommand {
    /**
     * Execute instruction chosen by user
     * @return 0 to exit program, 1 to continue
     */
    public int execute();
}
