; ModuleID = '/tmp/sea-ibokp4ow/2.pp.ms.o.bc'
source_filename = "2.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@llvm.used = appending global [16 x i8*] [i8* bitcast (void ()* @seahorn.fail to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void ()* @seahorn.fail to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void ()* @seahorn.fail to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void ()* @seahorn.fail to i8*)], section "llvm.metadata"

declare i64 @_nd_st_size1() local_unnamed_addr #0

declare i64 @_nd_st_size2() local_unnamed_addr #0

declare i64 @_nd_st_atim_tv_sec1() local_unnamed_addr #0

declare i64 @_nd_st_atim_tv_sec2() local_unnamed_addr #0

declare i64 @_nd_st_ctim_tv_sec1() local_unnamed_addr #0

declare i64 @_nd_st_ctim_tv_sec2() local_unnamed_addr #0

declare i64 @_nd_st_mtim_tv_sec1() local_unnamed_addr #0

declare i64 @_nd_st_mtim_tv_sec2() local_unnamed_addr #0

declare i32 @_nd_st_mode1() local_unnamed_addr #0

declare i32 @_nd_st_mode2() local_unnamed_addr #0

declare i32 @_nd_strcoll12() local_unnamed_addr #0

declare i32 @_nd_strcmp12() local_unnamed_addr #0

declare i32 @_nd_all_fmt() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_size() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_atime() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_ctime() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_mtime() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_dir() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_reverse() local_unnamed_addr #0

declare zeroext i1 @_nd_CONFIG_LOCALE_SUPPORT() local_unnamed_addr #0

declare zeroext i1 @_nd_CONFIG_LFS() local_unnamed_addr #0

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assume(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assume.not(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @seahorn.fail() #1

; Function Attrs: inaccessiblememonly nofree norecurse noreturn nounwind
declare void @verifier.error() #2

; Function Attrs: inaccessiblememonly
declare void @seahorn.fn.enter() local_unnamed_addr #3

declare i32 @verifier.nondet.0()

; Function Attrs: nounwind uwtable
define dso_local i32 @main() local_unnamed_addr #4 {
entry:
  call void @seahorn.fn.enter() #7
  %_0 = call i64 @_nd_st_size1() #7
  %_1 = call i64 @_nd_st_size2() #7
  %_2 = call i64 @_nd_st_atim_tv_sec1() #7
  %_3 = call i64 @_nd_st_atim_tv_sec2() #7
  %_4 = call i64 @_nd_st_ctim_tv_sec1() #7
  %_5 = call i64 @_nd_st_ctim_tv_sec2() #7
  %_6 = call i64 @_nd_st_mtim_tv_sec1() #7
  %_7 = call i64 @_nd_st_mtim_tv_sec2() #7
  %_8 = call i32 @_nd_st_mode1() #7
  %_9 = call i32 @_nd_st_mode2() #7
  %_10 = call i32 @_nd_strcoll12() #7
  %_11 = call i32 @_nd_strcmp12() #7
  %_12 = call i32 @_nd_all_fmt() #7
  %_13 = call zeroext i1 @_nd_sort_size() #7
  %_14 = call zeroext i1 @_nd_sort_atime() #7
  %_15 = call zeroext i1 @_nd_sort_ctime() #7
  %_16 = call zeroext i1 @_nd_sort_mtime() #7
  %_17 = call zeroext i1 @_nd_sort_dir() #7
  %_18 = call zeroext i1 @_nd_sort_reverse() #7
  %_19 = call zeroext i1 @_nd_CONFIG_LOCALE_SUPPORT() #7
  %_20 = call zeroext i1 @_nd_CONFIG_LFS() #7
  call void @verifier.assume(i1 %_20) #7
  %_21 = call i1 @nondet.bool()
  call void @verifier.assume.not(i1 %_21)
  br label %precall

precall:                                          ; preds = %entry
  br label %sortcmp

sortcmp:                                          ; preds = %precall
  call void @seahorn.fn.enter() #7
  br i1 %_13, label %_22, label %_25

_22:                                              ; preds = %sortcmp
  %_23 = add nsw i64 %_1, %_0
  %_24 = sub nsw i64 %_1, %_0
  br label %_51

_25:                                              ; preds = %sortcmp
  br i1 %_14, label %_26, label %_29

_26:                                              ; preds = %_25
  %_27 = add nsw i64 %_3, %_2
  %_28 = sub nsw i64 %_3, %_2
  br label %_51

_29:                                              ; preds = %_25
  br i1 %_15, label %_30, label %_33

_30:                                              ; preds = %_29
  %_31 = add nsw i64 %_5, %_4
  %_32 = sub nsw i64 %_5, %_4
  br label %_51

_33:                                              ; preds = %_29
  br i1 %_16, label %_34, label %_37

_34:                                              ; preds = %_33
  %_35 = sub nsw i64 %_7, %_6
  %_36 = sub nsw i64 %_7, %_6
  br label %_51

_37:                                              ; preds = %_33
  br i1 %_17, label %_38, label %_51

_38:                                              ; preds = %_37
  %_39 = and i32 %_9, 61440
  %_40 = icmp eq i32 %_39, 16384
  %_41 = zext i1 %_40 to i64
  %_42 = and i32 %_8, 61440
  %_43 = icmp eq i32 %_42, 16384
  %.neg.i = sext i1 %_43 to i64
  %_44 = add nsw i64 %.neg.i, %_41
  %_45 = and i32 %_9, 61440
  %_46 = icmp eq i32 %_45, 16384
  %_47 = zext i1 %_46 to i64
  %_48 = and i32 %_8, 61440
  %_49 = icmp eq i32 %_48, 16384
  %.neg8.i = sext i1 %_49 to i64
  %_50 = add nsw i64 %.neg8.i, %_47
  br label %_51

_51:                                              ; preds = %_38, %_37, %_34, %_30, %_26, %_22
  %.01.i = phi i64 [ %_23, %_22 ], [ %_27, %_26 ], [ %_31, %_30 ], [ %_35, %_34 ], [ %_44, %_38 ], [ 0, %_37 ]
  %.0.i = phi i64 [ %_24, %_22 ], [ %_28, %_26 ], [ %_32, %_30 ], [ %_36, %_34 ], [ %_50, %_38 ], [ 0, %_37 ]
  %_52 = icmp eq i64 %.01.i, 0
  %spec.select12.v.i = select i1 %_19, i32 %_10, i32 %_11
  %spec.select12.i = sext i32 %spec.select12.v.i to i64
  %.23.i = select i1 %_52, i64 %spec.select12.i, i64 %.01.i
  %_53 = icmp eq i64 %.0.i, 0
  %spec.select13.v.i = select i1 %_19, i32 %_10, i32 %_11
  %spec.select13.i = sext i32 %spec.select13.v.i to i64
  %.2.i = select i1 %_53, i64 %spec.select13.i, i64 %.0.i
  %.not.i = icmp eq i64 %.23.i, 0
  br i1 %.not.i, label %_57, label %_54

_54:                                              ; preds = %_51
  %.45.i = ashr i64 %.23.i, 32
  %_55 = srem i64 %.45.i, 2
  %_56 = add nsw i64 %.45.i, %_55
  br label %_57

_57:                                              ; preds = %_54, %_51
  %.56.i = phi i64 [ %_56, %_54 ], [ %.23.i, %_51 ]
  %.not7.i = icmp eq i64 %.2.i, 0
  br i1 %.not7.i, label %_62, label %_58

_58:                                              ; preds = %_57
  %_59 = ashr i64 %.2.i, 32
  %.4.i = select i1 %_20, i64 %_59, i64 0
  %_60 = srem i64 %.4.i, 2
  %_61 = add nsw i64 %.4.i, %_60
  br label %_62

_62:                                              ; preds = %_58, %_57
  %.5.i = phi i64 [ %_61, %_58 ], [ %.2.i, %_57 ]
  %_63 = trunc i64 %.56.i to i32
  %_64 = sub nsw i32 0, %_63
  %_65 = trunc i64 %.56.i to i32
  %_66 = select i1 %_18, i32 %_64, i32 %_65
  %_67 = trunc i64 %.5.i to i32
  %_68 = sub nsw i32 0, %_67
  %_69 = trunc i64 %.5.i to i32
  %_70 = select i1 %_18, i32 %_68, i32 %_69
  %_71 = icmp eq i32 %_66, %_70
  call void @verifier.assume.not(i1 %_71)
  br label %_72

_72:                                              ; preds = %_62
  br label %verifier.error

verifier.error:                                   ; preds = %_72
  call void @seahorn.fail()
  br label %verifier.error.split

verifier.error.split:                             ; preds = %verifier.error
  ret i32 42
}

declare i1 @nondet.bool()

declare i64 @verifier.nondet.sym.bound()

declare void @shadow.mem.load(i32, i32, i8*)

declare void @shadow.mem.trsfr.load(i32, i32, i8*)

declare i32 @shadow.mem.store(i32, i32, i8*)

declare i32 @shadow.mem.global.init(i32, i32, i8*)

declare i32 @shadow.mem.init(i32, i8*)

declare i32 @shadow.mem.arg.init(i32, i8*)

declare void @shadow.mem.arg.ref(i32, i32, i32, i8*)

declare i32 @shadow.mem.arg.mod(i32, i32, i32, i8*)

declare i32 @shadow.mem.arg.new(i32, i32, i32, i8*)

declare void @shadow.mem.in(i32, i32, i32, i8*)

declare void @shadow.mem.out(i32, i32, i32, i8*)

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assert(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assert.not(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare i1 @sea.is_dereferenceable(i8* nocapture, i64) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @sea.assert.if(i1, i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @sea.synth.assume(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @sea.synth.assert(i1) #1

; Function Attrs: nofree norecurse nounwind readonly
declare i1 @sea.is_modified(i8* nocapture) #5

; Function Attrs: nofree norecurse nounwind
declare void @sea.reset_modified(i8* nocapture) #6

; Function Attrs: nofree norecurse nounwind readonly
declare i1 @sea.is_read(i8* nocapture) #5

; Function Attrs: nofree norecurse nounwind
declare void @sea.reset_read(i8* nocapture) #6

; Function Attrs: nofree norecurse nounwind readonly
declare i1 @sea.is_alloc(i8* nocapture) #5

; Function Attrs: nofree norecurse nounwind
declare void @sea.tracking_on() #6

; Function Attrs: nofree norecurse nounwind
declare void @sea.tracking_off() #6

; Function Attrs: nofree norecurse nounwind
declare void @sea.free(i8* nocapture) #6

; Function Attrs: nofree norecurse nounwind
declare void @sea.set_shadowmem(i8, i8* nocapture, i8) #6

; Function Attrs: nofree norecurse nounwind readonly
declare i8 @sea.get_shadowmem(i8, i8* nocapture) #5

attributes #0 = { "frame-pointer"="none" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #1 = { inaccessiblememonly nofree norecurse nounwind }
attributes #2 = { inaccessiblememonly nofree norecurse noreturn nounwind }
attributes #3 = { inaccessiblememonly }
attributes #4 = { nounwind uwtable "frame-pointer"="none" "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #5 = { nofree norecurse nounwind readonly }
attributes #6 = { nofree norecurse nounwind }
attributes #7 = { nounwind }

!llvm.module.flags = !{!0, !1, !2, !3}
!llvm.ident = !{!4}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"PIC Level", i32 2}
!2 = !{i32 7, !"PIE Level", i32 2}
!3 = !{i32 7, !"uwtable", i32 1}
!4 = !{!"Ubuntu clang version 14.0.0-1ubuntu1"}
