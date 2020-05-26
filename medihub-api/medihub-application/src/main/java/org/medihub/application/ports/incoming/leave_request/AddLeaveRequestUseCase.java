package org.medihub.application.ports.incoming.leave_request;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface AddLeaveRequestUseCase {
    void addLeave(AddLeaveCommand addLeaveCommand);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddLeaveCommand extends SelfValidating<AddLeaveRequestUseCase.AddLeaveCommand> {
        @NotNull
        List<String> dates;
        @NotNull
        Integer type;

        public AddLeaveCommand(
                List<String> dates,
                String type){
            this.dates = dates;
            this.type = getType(type);
            this.validateSelf();
        }

        private Integer getType(String type) {
            if(type.toLowerCase().equals("vacation")) {
                return MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.VACATION.getOrdinal();
            }
            return MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.LEAVE.getOrdinal();
        }
    }
}
