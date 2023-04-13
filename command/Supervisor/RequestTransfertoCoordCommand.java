package command.Supervisor;

import java.time.LocalDateTime;
import command.Command;
import FYPMS.FYPMS1;
import FYPMS.request.*;

public class RequestTransfertoCoordCommand implements Command {
    private String requester;

    public RequestTransfertoCoordCommand(String requester) {
        this.requester = requester;
    }

    RequestList RequestList = FYPMS1.getRequestList();

    public void execute() {
        LocalDateTime statusChangeTime = LocalDateTime.now();
        Request request = new Request(requester, RequestRelationship.SUPERVISORCoordinator, statusChangeTime,
                "FYP Coordinator", RequestStatus.PENDING);
        RequestList.add(request);
    }
}
