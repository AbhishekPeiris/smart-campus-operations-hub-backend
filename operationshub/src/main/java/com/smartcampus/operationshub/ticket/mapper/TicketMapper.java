package com.smartcampus.operationshub.ticket.mapper;

import com.smartcampus.operationshub.ticket.dto.response.*;
import com.smartcampus.operationshub.ticket.model.IncidentTicket;

import java.util.List;
import java.util.stream.Collectors;

public class TicketMapper {

    public static IncidentTicketResponse toIncidentTicketResponse(IncidentTicket ticket) {
        return IncidentTicketResponse.builder()
                .id(ticket.getId())
                .ticketCode(ticket.getTicketCode())
                .ticketTitle(ticket.getTicketTitle())
                .status(ticket.getStatus())
                .priorityLevel(ticket.getPriorityLevel())
                .createdByUserId(ticket.getCreatedByUserId())
                .createdByName(ticket.getCreatedByName())
                .assignedTechnicianId(ticket.getAssignedTechnicianId())
                .assignedTechnicianName(ticket.getAssignedTechnicianName())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .build();
    }

    public static TicketDetailsResponse toTicketDetailsResponse(
            IncidentTicket ticket,
            List<TicketCommentResponse> comments,
            List<TicketAttachmentResponse> attachments,
            List<TechnicianUpdateLogResponse> updates
    ) {
        return TicketDetailsResponse.builder()
                .id(ticket.getId())
                .ticketCode(ticket.getTicketCode())
                .incidentCategory(ticket.getIncidentCategory())
                .ticketTitle(ticket.getTicketTitle())
                .description(ticket.getDescription())
                .priorityLevel(ticket.getPriorityLevel())
                .status(ticket.getStatus())
                .createdByUserId(ticket.getCreatedByUserId())
                .createdByName(ticket.getCreatedByName())
                .preferredContactName(ticket.getPreferredContactName())
                .preferredContactEmailAddress(ticket.getPreferredContactEmailAddress())
                .preferredContactPhoneNumber(ticket.getPreferredContactPhoneNumber())
                .assignedTechnicianId(ticket.getAssignedTechnicianId())
                .assignedTechnicianName(ticket.getAssignedTechnicianName())
                .resourceIdentifier(ticket.getResourceIdentifier())
                .resourceName(ticket.getResourceName())
                .resourceType(ticket.getResourceType())
                .locationIdentifier(ticket.getLocationIdentifier())
                .locationName(ticket.getLocationName())
                .resolutionNotes(ticket.getResolutionNotes())
                .rejectionReason(ticket.getRejectionReason())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .comments(comments)
                .attachments(attachments)
                .technicianUpdates(updates)
                .build();
    }

    public static List<IncidentTicketResponse> toIncidentTicketList(List<IncidentTicket> tickets) {
        return tickets.stream()
                .map(TicketMapper::toIncidentTicketResponse)
                .collect(Collectors.toList());
    }
}